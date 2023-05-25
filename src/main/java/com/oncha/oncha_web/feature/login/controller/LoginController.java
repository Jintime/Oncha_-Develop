package com.oncha.oncha_web.feature.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/disable")
    public String Disable(){ return "login/register";}

}
