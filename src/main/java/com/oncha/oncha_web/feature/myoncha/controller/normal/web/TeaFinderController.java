package com.oncha.oncha_web.feature.myoncha.controller.normal.web;

import org.springframework.web.bind.annotation.GetMapping;

public class TeaFinderController {
    @GetMapping("/myoncha")
    public String MyOncha(){
        return "user/category/myOncha/myOncha";
    }
    @GetMapping("/finder")
    public String TeaFinder(){return "user/category/myOncha/teaFinder";}

}
