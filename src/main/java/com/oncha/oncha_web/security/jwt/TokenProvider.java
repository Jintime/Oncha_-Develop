package com.oncha.oncha_web.security.jwt;


import com.oncha.oncha_web.exception.NotEqualRefreshTokenException;
import com.oncha.oncha_web.exception.RedisEntityNotFoundException;
import com.oncha.oncha_web.redis.RefreshTokenInfo;
import com.oncha.oncha_web.redis.RefreshTokenRepository;
import com.oncha.oncha_web.security.auth.PrincipalDetails;
import com.oncha.oncha_web.util.CookieUtil;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TokenProvider implements InitializingBean {
    public static final String ACCESS_TOKEN_KEY = "access_token";
    public static final String REFRESH_TOKEN_KEY = "refresh_token";

    public static final String TOKEN_START_WITH = "Bearer ";
    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
    private static final String AUTHORITIES_KEY = "auth";
    private final String secretKey;
    private final long tokenValidityInMilliSeconds;

    private final String refreshKey;

    private final long refreshValidityInSeconds;

    private Key accessTypeKey;

    private Key refreshTypeKey;

    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public TokenProvider(
            //.yml에서 설정한 jwt관련 설정들을 불러와 상수 할당
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds,
            @Value("${jwt.refresh-secret}") String refreshKey,
            @Value("${jwt.refresh-validity-in-seconds}") long refreshValidityInSeconds,
            RefreshTokenRepository refreshTokenRepository) {
        this.secretKey = secretKey;
        this.refreshKey = refreshKey;
        this.refreshValidityInSeconds = refreshValidityInSeconds; //redis 저장기한고 별도.. 항상 redis 보다는 클것! - 크게 의미없음
        this.tokenValidityInMilliSeconds = tokenValidityInSeconds;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    //빈이 생성이되고 주입을 받은 후에 secret값을 base64디코딩해서 키에 할당
    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytesSecret = Decoders.BASE64.decode(secretKey);
        byte[] keyBytesRefresh = Decoders.BASE64.decode(refreshKey);
        this.accessTypeKey = Keys.hmacShaKeyFor(keyBytesSecret);
        this.refreshTypeKey = Keys.hmacShaKeyFor(keyBytesRefresh);
    }

    public String createToken(Long id, String role) {

        Date validity = new Date(System.currentTimeMillis() + this.tokenValidityInMilliSeconds);//현재시간+토큰만료기간

        String jwt = Jwts.builder()
                .setSubject(String.valueOf(id))
                .claim(AUTHORITIES_KEY, role)
                .signWith(accessTypeKey, SignatureAlgorithm.HS256)
                .setExpiration(validity)
                .compact();

        return jwt;
    }

    public String createRefresh() {
        long now = (new Date()).getTime(); //현재시간
        Date validity = new Date(now + this.refreshValidityInSeconds);//현재시간+토큰만료기간
        String jwt = Jwts.builder()
                .setSubject(UUID.randomUUID().toString())
                .signWith(refreshTypeKey, SignatureAlgorithm.HS256)
                .setExpiration(validity)
                .compact();

        return jwt;
    }


    private Claims getClaimsByToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(accessTypeKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Collection<GrantedAuthority> getAuthoritiesByClaims(Claims claims) {
        return Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
    }

    private String getRoleByClaims(Claims claims) {
        return claims.get(AUTHORITIES_KEY).toString();
    }

    private Long getSubjectByClaims(Claims claims) {
        Long id = null;
        try {
            id = Long.valueOf(claims.getSubject());
        } catch (NumberFormatException e) {
            throw new RuntimeException("jwt id 형식이 Long 이 아닙니다.");
        }
        return id;
    }

    //토큰을 파라미터로 받아 토큰에 담겨있는 권한정보를 이용해서 Authentication 객체를 리턴하는 메소드
    public Authentication getAuthentication(String token) {
        //claims 추출
        Claims claims = getClaimsByToken(token);

        //권한 목록 추출
        Collection<GrantedAuthority> authorities = getAuthoritiesByClaims(claims);

        //subject에서 id 추출
        Long id = getSubjectByClaims(claims);

        //권한 한줄로 추출
        String role = getRoleByClaims(claims);

        return new UsernamePasswordAuthenticationToken(new PrincipalDetails(id, role), token, authorities);
    }

    private boolean processNewJwtTokenInCookie(Claims claims, String refresh ,HttpServletResponse response) {
        //id. 역할 추출
        Long id = getSubjectByClaims(claims);
        String role = getRoleByClaims(claims);

        //id로 redis key 생성
        String tokenKey = createTokenKey(id);
        System.out.println("asdasdasdasda");
        //redis 검색
        RefreshTokenInfo refreshTokenInfo = refreshTokenRepository.findById(tokenKey)
                .orElseThrow(() -> new RedisEntityNotFoundException(tokenKey, String.valueOf(refresh)));

        //redis에 있는 refresh와 현재 refresh 대조
        if (!refreshTokenInfo.getRefreshToken().equals(refresh))
            throw new NotEqualRefreshTokenException(refreshTokenInfo.getRefreshToken(), refresh);

        //새로운 access,refresh token 생성
        String newAccessToken = createToken(id, role);
        String newRefreshToken = createRefresh();

        //redis에 새로운 refreshtoken 저장
        refreshTokenInfo.setRefreshToken(newRefreshToken);

        //쿠키에 이전 토큰 버리고 새로운 token 저장
        CookieUtil.removeTokenInCookie(response);
        CookieUtil.setTokenInCookie(response, newAccessToken, newRefreshToken);

        return true;
    }

    public String createTokenKey(Long id) {
        return String.format("rToken_%d", id);
    }


    //토큰을 파라미터로 받아 토큰의 유효성 검사를 할 수있는 validate Token 메소드
    public boolean validateToken(String token, String refresh, HttpServletResponse response) {
        try {
            Jwts.parserBuilder().setSigningKey(accessTypeKey).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.info("잘못된 JWT 서명입니다");
        } catch (ExpiredJwtException e) {
            logger.info("만료된 JWT 토큰입니다, refresh 시작");
            return processNewJwtTokenInCookie(e.getClaims(), refresh ,response);
        } catch (RedisEntityNotFoundException e) {
            logger.info(String.format("Redis에서 해당 리프레시 토큰을 찾을수 없습니다 token:%s, expect:%s", e.getKey(), e.getExpect()));
            CookieUtil.removeTokenInCookie(response);
        } catch (NotEqualRefreshTokenException e) {
            logger.info(String.format("redis에 저장된 id와 jwt에 저장된 id가 다릅니다. redisId:%s, jwtId:%s",e.getInfoId(), e.getJwtId()));
            CookieUtil.removeTokenInCookie(response);
        } catch (UnsupportedJwtException e) {
            logger.info("지원 되지 않는 JWT 토큰입니다. 쿠키 삭제");
            CookieUtil.removeTokenInCookie(response);
        } catch (IllegalArgumentException e) {
            logger.info("JWT 토큰이 잘못되었습니다. 쿠키 삭제");
            CookieUtil.removeTokenInCookie(response);
        } catch (Exception e) {
            System.out.println("비정상적인 토큰입니다. 쿠키 삭제");
            CookieUtil.removeTokenInCookie(response);
        }
        return false;
    }
}
