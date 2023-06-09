package com.oncha.oncha_web.security.filter;


import com.oncha.oncha_web.security.jwt.JwtTokenUtil;
import com.oncha.oncha_web.security.jwt.TokenProvider;
import com.oncha.oncha_web.security.jwt.redis.exception.CustomJwtException;
import com.oncha.oncha_web.security.jwt.redis.feature.TokenDto;
import com.oncha.oncha_web.util.CookieUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter { //처음들어올때 한번처리하고 이후 처리를 하지않는 필터 GenericFilterBean은 들어올때, 나갈때 처리
    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final TokenProvider tokenProvider;
//
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        try {
            String jwt = resolveTokenByCookie(request);
            String refresh = resolveRefreshByCookie(request);
            if (StringUtils.hasText(jwt)) {
                try {
                    if (tokenProvider.validateToken(jwt, response)) {
                        setAuthenticationInSecurityContext(jwt, requestURI);
                    } else {
                        logger.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
                        resetRefreshCookie(response);
                    }
                } catch (ExpiredJwtException e) {
                    try {
                        logger.debug("리프레시 시작");
                        TokenDto tokenDto = tokenProvider.getNewRegisteredTokenByClaims(e.getClaims(), refresh);
                        setAuthenticationInSecurityContext(tokenDto.getAccess(), requestURI);
                        resetRefreshCookie(response);
                    }catch (CustomJwtException ce) {
                        logger.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
                        resetRefreshCookie(response);
                    }
                }
            } else {
                logger.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
            }

        } catch (Exception e) {
            logger.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
            resetRefreshCookie(response);
        }

        filterChain.doFilter(request, response);
    }

    private void setAuthenticationInSecurityContext(String access, String requestURI) {
        Authentication authentication = tokenProvider.getAuthentication(access);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        logger.debug("Security Context에 '{}' 인증정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
    }

    private void resetRefreshCookie(HttpServletResponse response) {
        JwtTokenUtil.removeTokenInCookie(response);
    }

    //request header에서 토큰정보를 꺼내오기 위한 메소드
    private String resolveTokenByCookie (HttpServletRequest request) {
        Cookie cookie = CookieUtil.getCookie(request, TokenProvider.ACCESS_TOKEN_KEY).orElse(null);
        return cookie == null ? null : cookie.getValue();
    }

    private String resolveRefreshByCookie (HttpServletRequest request) {
        Cookie cookie = CookieUtil.getCookie(request, TokenProvider.REFRESH_TOKEN_KEY).orElse(null);
        return cookie == null ? null : cookie.getValue();
    }

    //request header에서 토큰정보를 꺼내오기 위한 메소드
    private String resolveTokenByHeader (HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TokenProvider.TOKEN_START_WITH)){
            return bearerToken.substring(7); // Bearer 띄어쓰기 포함 글자 뒤부터 나머지 글자 가져오기
        }
        return null;
    }
}
