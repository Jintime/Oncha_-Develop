package com.oncha.oncha_web.security.jwt;

import com.oncha.oncha_web.util.CookieUtil;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenUtil {

    public static void removeTokenInCookie(HttpServletResponse response) {
        response.addCookie(CookieUtil.getEmptyCookie(TokenProvider.ACCESS_TOKEN_KEY, "/"));
        response.addCookie(CookieUtil.getEmptyCookie(TokenProvider.REFRESH_TOKEN_KEY, "/"));
    }


    public static void setTokenInCookie(HttpServletResponse response, String access,
        String refresh) {
        response.addCookie(CookieUtil.getTokenCookie(TokenProvider.ACCESS_TOKEN_KEY, access));
        response.addCookie(CookieUtil.getTokenCookie(TokenProvider.REFRESH_TOKEN_KEY, refresh));
    }

}
