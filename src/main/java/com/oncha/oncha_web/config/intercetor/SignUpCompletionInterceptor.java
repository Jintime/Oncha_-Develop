package com.oncha.oncha_web.config.intercetor;

import com.oncha.oncha_web.security.auth.PrincipalDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

public class SignUpCompletionInterceptor implements HandlerInterceptor {

    private String endPoint = "/register";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        if (request.getRequestURI().contains(endPoint)) {
            return true;
        }
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Object principal = authentication.getPrincipal();
            PrincipalDetails principalDetails = (PrincipalDetails) principal;
            if (!principalDetails.isAllowed()) {
                response.sendRedirect(endPoint);
                return false;
            }
        } catch (Exception e) {

        }
        return true;
    }
}
