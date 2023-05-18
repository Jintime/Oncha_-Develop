package com.oncha.oncha_web.util;

import com.oncha.oncha_web.security.jwt.TokenProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

public class CookieUtil {

    public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return Optional.of(cookie);
                }
            }
        }
        return Optional.empty();
    }

    public static void setTokenInCookie(HttpServletResponse response, String access, String refresh) {
        setAccessInCookie(response, access);
        setRefreshInCookie(response, refresh);
    }

    private static Cookie getTokenCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/"); //이거 무조건 설정해줘야한다!!!!!!!!!!!!!!!
        return cookie;
    }


    private static void setRefreshInCookie(HttpServletResponse response, String refresh) {
        response.addCookie(getTokenCookie(TokenProvider.REFRESH_TOKEN_KEY, refresh));
    }

    private static void setAccessInCookie(HttpServletResponse response, String access) {
        response.addCookie(getTokenCookie(TokenProvider.ACCESS_TOKEN_KEY, access));
    }

    public static void removeTokenInCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(TokenProvider.ACCESS_TOKEN_KEY, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        Cookie cookie2 = new Cookie(TokenProvider.REFRESH_TOKEN_KEY, null);
        cookie2.setMaxAge(0);
        cookie2.setPath("/");
        response.addCookie(cookie2);
    }


}
