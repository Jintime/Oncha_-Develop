package com.oncha.oncha_web.feature.tea_finder;

import org.springframework.web.bind.annotation.GetMapping;

public class TeaFinderController {
    @GetMapping("/finder")
    public String TeaFinder(){return "user/category/myOncha/teaFinder";}

}
