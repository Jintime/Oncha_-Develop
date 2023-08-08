package com.oncha.oncha_web.feature.myoncha.controller.normal.web;

import com.oncha.oncha_web.aop.annotation.SetUserInfoInModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeaFinderController {

    @SetUserInfoInModel
    @GetMapping("/myoncha")
    public String MyOncha(Model model){
        if(!model.containsAttribute("user")) return "user/store/order/userCheck";
        else return "user/category/myOncha/myOncha";
    }
    @GetMapping("/finder")
    public String TeaFinder(){return "user/category/myOncha/teaFinder";}

    @GetMapping("/tea-finder")
    public String TeaFindTest(){
        return "user/category/myOncha/teaTest";
    }

}
