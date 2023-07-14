package com.oncha.oncha_web.security.oauth;

import com.oncha.oncha_web.domain.user.model.Role;
import com.oncha.oncha_web.security.auth.PrincipalDetails;
import com.oncha.oncha_web.security.service.LoginSuccessService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class OAuthLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final LoginSuccessService loginSuccessService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication)
        throws IOException, ServletException {
        // 최초 로그인이라면 회원가입 처리를 한다. //이미 서비스에서 회원가입 처리 되었음
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        Long id = principalDetails.getId();
        Role role = principalDetails.getRole();
        boolean allow = principalDetails.isAllowed();
        loginSuccessService.processingLogin(response, id, role, allow);
        log.info(String.format("id: %d, allow:%s login success", id, allow ? "true" : "false"));
        response.sendRedirect("/");
    }

}
