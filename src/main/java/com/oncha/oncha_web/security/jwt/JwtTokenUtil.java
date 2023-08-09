package com.oncha.oncha_web.security.jwt;

import com.oncha.oncha_web.util.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    private final CookieUtil cookieUtil;

    public Cookie getAccessTokenInCookie(HttpServletRequest request) {
        return cookieUtil.getCookie(request, TokenProvider.ACCESS_TOKEN_KEY).orElse(null);
    }

    public Cookie getRefreshTokenInCookie(HttpServletRequest request) {
        return cookieUtil.getCookie(request, TokenProvider.REFRESH_TOKEN_KEY).orElse(null);
    }

    public void removeTokenInCookie(HttpServletResponse response) {
        response.addCookie(cookieUtil.getEmptyCookie(TokenProvider.ACCESS_TOKEN_KEY, "/"));
        response.addCookie(cookieUtil.getEmptyCookie(TokenProvider.REFRESH_TOKEN_KEY, "/"));
    }

    public void setTokenInCookie(HttpServletResponse response, String access,
        String refresh) {
        response.addCookie(cookieUtil.getTokenCookie(TokenProvider.ACCESS_TOKEN_KEY, access));
        response.addCookie(cookieUtil.getTokenCookie(TokenProvider.REFRESH_TOKEN_KEY, refresh));
    }

}
