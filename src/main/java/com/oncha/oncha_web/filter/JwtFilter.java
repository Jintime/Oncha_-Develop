package com.oncha.oncha_web.filter;


import com.oncha.oncha_web.security.jwt.TokenProvider;
import com.oncha.oncha_web.util.CookieUtil;
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
        String jwt = resolveTokenByCookie(request);
        String refresh = resolveRefreshByCookie(request);

        String requestURI = request.getRequestURI();
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt, refresh, response)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.debug("Security Context에 '{}' 인증정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
        } else {
            logger.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
        }

        filterChain.doFilter(request, response);
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
