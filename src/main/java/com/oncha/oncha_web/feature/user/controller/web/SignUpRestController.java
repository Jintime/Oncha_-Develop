package com.oncha.oncha_web.feature.user.controller.web;

import com.oncha.oncha_web.domain.user.model.Role;
import com.oncha.oncha_web.exception._40x.NotLoginException;
import com.oncha.oncha_web.feature.user.model.RegisterRequest;
import com.oncha.oncha_web.feature.user.service.MemberService;
import com.oncha.oncha_web.security.auth.PrincipalDetails;
import com.oncha.oncha_web.security.service.LoginSuccessService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class SignUpRestController {

    private final MemberService memberService;

    private final LoginSuccessService loginSuccessService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public @ResponseBody void signAdd(@RequestBody @Valid RegisterRequest request,
        HttpServletResponse response,
        @AuthenticationPrincipal PrincipalDetails principalDetails)
        throws IOException, NotLoginException {

        Long id = principalDetails.getId();
        Role role = principalDetails.getRole();
        boolean changeAllow = memberService.register(id, request);
        loginSuccessService.processingLogin(response, id, role, changeAllow);
    }

}
