package com.oncha.oncha_web.feature;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/index")
    public String Home(){return "index_test";}



}
