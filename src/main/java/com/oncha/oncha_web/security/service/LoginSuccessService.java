package com.oncha.oncha_web.security.service;

import com.oncha.oncha_web.domain.user.model.Role;
import com.oncha.oncha_web.feature.user.service.MemberService;
import com.oncha.oncha_web.security.jwt.JwtTokenUtil;
import com.oncha.oncha_web.security.jwt.TokenProvider;
import com.oncha.oncha_web.security.jwt.redis.feature.RefreshTokenRedisService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginSuccessService {

    private final TokenProvider tokenProvider;

    private final MemberService memberService;

    public void processingLogin(HttpServletResponse response, Long id, Role role, boolean allow)
        throws IOException {
        String token = tokenProvider.createToken(id, role, allow);
        String refreshToken = tokenProvider.createRefresh();
        memberService.setNewRefresh(id, refreshToken);
        JwtTokenUtil.setTokenInCookie(response, token, refreshToken);
    }
}
