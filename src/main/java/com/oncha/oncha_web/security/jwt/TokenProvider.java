package com.oncha.oncha_web.security.jwt;



import com.oncha.oncha_web.security.jwt.redis.exception.CustomJwtException;
import com.oncha.oncha_web.security.jwt.redis.feature.RefreshTokenRedisService;
import com.oncha.oncha_web.security.jwt.redis.repository.RefreshTokenInfo;
import com.oncha.oncha_web.security.jwt.redis.repository.RefreshTokenRepository;
import com.oncha.oncha_web.security.auth.PrincipalDetails;
import com.oncha.oncha_web.security.jwt.redis.feature.TokenDto;
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

    private final long refreshValidityMillInSeconds;

    private Key accessTypeKey;

    private Key refreshTypeKey;

    private final RefreshTokenRedisService refreshTokenRedisService;

    @Autowired
    public TokenProvider(
            //.yml에서 설정한 jwt관련 설정들을 불러와 상수 할당
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.token-validity-in-Mill-Seconds}") long tokenValidityMillInSeconds,
            @Value("${jwt.refresh-secret}") String refreshKey,
            @Value("${jwt.refresh-validity-in-Mill-Seconds}") long refreshValidityMillInSeconds,
            RefreshTokenRedisService refreshTokenRedisService ) {
        this.secretKey = secretKey;
        this.refreshKey = refreshKey;
        this.refreshValidityMillInSeconds = refreshValidityMillInSeconds; //redis 저장기한고 별도.. 항상 redis 보다는 클것! - 크게 의미없음
        this.tokenValidityInMilliSeconds = tokenValidityMillInSeconds;
        this.refreshTokenRedisService = refreshTokenRedisService;
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
        Date validity = new Date(now + this.refreshValidityMillInSeconds);//현재시간+토큰만료기간
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

    //토큰을 파라미터로 받아 토큰에 담겨있는 권한정보를 이용해서 Authentication 객체를 리턴하는 메소드
    public Authentication getAuthenticationWithClaims(String token, Claims claims) {
        //권한 목록 추출
        Collection<GrantedAuthority> authorities = getAuthoritiesByClaims(claims);

        //subject에서 id 추출
        Long id = getSubjectByClaims(claims);

        //권한 한줄로 추출
        String role = getRoleByClaims(claims);

        return new UsernamePasswordAuthenticationToken(new PrincipalDetails(id, role), token, authorities);
    }

    public TokenDto getNewRegisteredTokenByClaims(Claims claims, String refresh) throws CustomJwtException {
        //id. 역할 추출
        Long id = getSubjectByClaims(claims);
        String role = getRoleByClaims(claims);

        //id로 redis key 생성
        String tokenKey = createTokenKey(id);

        //새로운 access,refresh token 생성
        String newAccessToken = createToken(id, role);
        String newRefreshToken = createRefresh();

        //기존 것들을 비교하여 올바르면 새로운 값을 집어넣기
        refreshTokenRedisService.processingResetRefreshToken(tokenKey, refresh, newRefreshToken);

        return new TokenDto(newAccessToken, newRefreshToken);
    }

    public String createTokenKey(Long id) {
        return String.format("rToken_%d", id);
    }


    //토큰을 파라미터로 받아 토큰의 유효성 검사를 할 수있는 validate Token 메소드
    public boolean validateToken(String token, HttpServletResponse response) throws ExpiredJwtException {
        try {
            Jwts.parserBuilder().setSigningKey(accessTypeKey).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.info("잘못된 JWT 서명입니다");
        } catch (ExpiredJwtException e) {
            logger.info("만료된 JWT 토큰입니다");
            throw e;
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
