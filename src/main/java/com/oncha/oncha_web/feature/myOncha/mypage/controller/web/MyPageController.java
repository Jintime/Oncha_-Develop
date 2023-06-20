package com.oncha.oncha_web.feature.myOncha.mypage.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

    private static final String BASE_PATH = "user/category/MyOncha";
    @GetMapping
    public String MyPage(){return BASE_PATH+"/myOncha";}


    @GetMapping("/{id}")
    public String MyPageDetail(@PathVariable Long id){
        return BASE_PATH+"/myPageDetail";
    }
}
