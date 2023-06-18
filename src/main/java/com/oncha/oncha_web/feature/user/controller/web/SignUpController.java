package com.oncha.oncha_web.feature.user.controller.web;

import com.oncha.oncha_web.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class SignUpController {

    @GetMapping
    @PreAuthorize("isAuthenticated()")
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
