package com.oncha.oncha_web.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class RefererFilter extends OncePerRequestFilter {

    private static final String ALLOWED_DOMAIN = "localhost";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String referer = request.getHeader("Referer");

        // Check if referer matches your desired domain
        if (referer != null && referer.contains(ALLOWED_DOMAIN)) {
            // Request is from your domain, continue processing
            filterChain.doFilter(request, response);
        } else {
            // Request is not from your domain, reject it
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
        }
    }
}