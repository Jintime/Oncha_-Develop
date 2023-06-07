package com.oncha.oncha_web.feature.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class SignUpController {

    @GetMapping
    public String registerPage() {
        return "login/register";
    }

    @PostMapping
    public void register() {

    }
}
