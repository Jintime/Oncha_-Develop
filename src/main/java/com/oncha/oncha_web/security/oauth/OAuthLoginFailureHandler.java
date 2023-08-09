package com.oncha.oncha_web.security.oauth;

import com.oncha.oncha_web.mail.MailingService;
import com.oncha.oncha_web.security.jwt.JwtTokenUtil;
import com.oncha.oncha_web.util.CookieUtil;
import com.oncha.oncha_web.util.ExceptionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuthLoginFailureHandler implements AuthenticationFailureHandler {

    private final MailingService mailingService;
    private String errorSubject = "oauth2 로그인 실패 오류입니다.";
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        exception.printStackTrace();
        mailingService.sendErrorMessage(errorSubject, ExceptionUtil.getStackTrace(exception));
        jwtTokenUtil.removeTokenInCookie(response);
        log.info("login fail");
        response.sendRedirect("/login");
    }
}
