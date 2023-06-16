package com.oncha.oncha_web.feature.user.controller;

import com.oncha.oncha_web.domain.user.model.Role;
import com.oncha.oncha_web.exception._40x.NotLoginException;
import com.oncha.oncha_web.feature.user.model.RegisterRequest;
import com.oncha.oncha_web.feature.user.service.MemberService;
import com.oncha.oncha_web.security.auth.PrincipalDetails;
import com.oncha.oncha_web.security.service.LoginSuccessService;
import com.oncha.oncha_web.util.SecurityUtil;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class SignUpController {

    private final MemberService memberService;

    private final LoginSuccessService loginSuccessService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public String signAdd(@ModelAttribute RegisterRequest request,
        HttpServletResponse response,
        @AuthenticationPrincipal PrincipalDetails principalDetails)
        throws IOException, NotLoginException {

        Long id = principalDetails.getId();
        Role role = principalDetails.getRole();
        boolean changeAllow = memberService.signAdd(id, request);
        loginSuccessService.processingLogin(response, id, role, changeAllow);
        return "redirect:/";
    }

    @GetMapping
    public String registerPage() {
        if (!SecurityUtil.isLogin()) {
            return "redirect:/";
        }
        if (SecurityUtil.getAllow()) {
            return "redirect:/";
        }
        return "login/register";
    }

}
