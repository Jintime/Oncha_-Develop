package com.oncha.oncha_web.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {

    public String domain;

    public CookieUtil (@Value("${cookie.domain}") String domain) {
        this.domain = domain;
    }

    public Optional<Cookie> getCookie(HttpServletRequest request, String name) {
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

    public Cookie getTokenCookie(String key, String value) {
        return getTokenCookie(key, value, null, "/");
    }

    public Cookie getTokenCookie(String key, String value, Integer maxAge) {
        return getTokenCookie(key, value, maxAge, "/");
    }

    public Cookie getTokenCookie(String key, String value, String path) {
        return getTokenCookie(key, value, null, path);
    }

    public Cookie getTokenCookie(String key, String value, Integer maxAge, String path) {

        Cookie cookie = new Cookie(key, value);
        cookie.setHttpOnly(true);
//        cookie.setSecure(true); https 가 아니면 넘어가지 않게함
        cookie.setDomain(domain);
        if (maxAge != null) {
            cookie.setMaxAge(maxAge);
        }
        cookie.setPath(path);
        return cookie;
    }

    public Cookie getEmptyCookie(String key, String path) {
        Cookie cookie = new Cookie(key, null);
        cookie.setDomain(domain);
        cookie.setMaxAge(0);
        cookie.setPath(path);
        return cookie;
    }

}
