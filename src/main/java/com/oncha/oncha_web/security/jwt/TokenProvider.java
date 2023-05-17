package com.oncha.oncha_web.security.jwt;


import com.oncha.oncha_web.domain.user.model.MemberRepository;
import com.oncha.oncha_web.security.auth.PrincipalDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
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
import java.util.stream.Collectors;

@Component
public class TokenProvider implements InitializingBean {
    public static final String ACCESS_TOKEN_KEY = "access_token";
    public static final String REFRESH_TOKEN_KEY = "refresh_token";

    public static final String TOKEN_START_WITH = "Bearer ";
    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
    private static final String AUTHORITIES_KEY = "auth";
    private final String secret;
    private final long tokenValidityInMilliSeconds;

    private final String refresh;

    private final long refreshValidityInSeconds;

    private Key accessKey;

    private Key refreshKey;

    @Autowired
    public TokenProvider(
            //.yml에서 설정한 jwt관련 설정들을 불러와 상수 할당
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds,
            @Value("${jwt.refresh-secret}") String refresh,
            @Value("${jwt.refresh-validity-in-seconds}") long refreshValidityInSeconds,
            MemberRepository memberRepository) {
        this.secret = secret;
        this.refresh = refresh;
        this.refreshValidityInSeconds = refreshValidityInSeconds;
        this.tokenValidityInMilliSeconds = tokenValidityInSeconds;
    }

    //빈이 생성이되고 주입을 받은 후에 secret값을 base64디코딩해서 키에 할당
    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytesSecret = Decoders.BASE64.decode(secret);
        byte[] keyBytesRefresh = Decoders.BASE64.decode(refresh);
        this.accessKey = Keys.hmacShaKeyFor(keyBytesSecret);
        this.refreshKey = Keys.hmacShaKeyFor(keyBytesRefresh);
    }

    public String createToken(Long id, String role) {

        Date validity = new Date(System.currentTimeMillis() + this.tokenValidityInMilliSeconds);//현재시간+토큰만료기간

        String jwt = Jwts.builder()
                .setSubject(String.valueOf(id))
                .claim(AUTHORITIES_KEY, role)
                .signWith(accessKey, SignatureAlgorithm.HS256)
                .setExpiration(validity)
                .compact();

        return jwt;
    }

    public String createRefresh(Long id, String role) {

        long now = (new Date()).getTime(); //현재시간
        Date validity = new Date(now + this.refreshValidityInSeconds);//현재시간+토큰만료기간
        String jwt = Jwts.builder()
                .setSubject(String.valueOf(id))
                .claim(AUTHORITIES_KEY, role)
                .signWith(refreshKey, SignatureAlgorithm.HS256)
                .setExpiration(validity)
                .compact();

        return jwt;
    }


    //토큰을 파라미터로 받아 토큰에 담겨있는 권한정보를 이용해서 Authentication 객체를 리턴하는 메소드
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(accessKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        Long id = null;
        String role = claims.get(AUTHORITIES_KEY).toString();
        try {
            id = Long.valueOf(claims.getSubject());
        } catch (NumberFormatException e) {
            throw new RuntimeException("jwt id 형식이 Long 이 아닙니다.");
        }

        return new UsernamePasswordAuthenticationToken(new PrincipalDetails(id, role), token, authorities);
    }

    //토큰을 파라미터로 받아 토큰의 유효성 검사를 할 수있는 validate Token 메소드
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(accessKey).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.info("잘못된 JWT 서명입니다");
        } catch (ExpiredJwtException e) {
            logger.info("만료된 JWT 토큰입니다");
        } catch (UnsupportedJwtException e) {
            logger.info("지원 되지 않는 JWT 토큰입니다");
        } catch (IllegalArgumentException e) {
            logger.info("JWT 토큰이 잘못되었습니다.");
        }catch (Exception e) {
            System.out.println("뭐선일이여");
        }
        return false;
    }
}
