package com.oncha.oncha_web.security.oauth;

import com.oncha.oncha_web.security.jwt.redis.feature.RefreshTokenRedisService;
import com.oncha.oncha_web.security.jwt.redis.repository.RefreshTokenInfo;
import com.oncha.oncha_web.security.jwt.redis.repository.RefreshTokenRepository;
import com.oncha.oncha_web.security.auth.PrincipalDetails;
import com.oncha.oncha_web.security.jwt.TokenProvider;
import com.oncha.oncha_web.security.service.LoginSuccessService;
import com.oncha.oncha_web.util.CookieUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class OAuthLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final LoginSuccessService loginSuccessService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        // 최초 로그인이라면 회원가입 처리를 한다. //이미 서비스에서 회원가입 처리 되었음
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        Long id = principalDetails.getId();
        String role = principalDetails.getRole();
        boolean allow = principalDetails.isAllowed();
        loginSuccessService.processingLogin(response, id, role, allow);
        response.sendRedirect("/");
    }

}
