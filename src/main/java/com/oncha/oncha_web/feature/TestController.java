package com.oncha.oncha_web.feature;

import com.oncha.oncha_web.aop.annotation.SetUserInfoInModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aspect/test")
public class TestController {

    @GetMapping
    @SetUserInfoInModel
    public String test(Model model) {
        System.out.println("aa");
        return "aspectTest";
    }

}
