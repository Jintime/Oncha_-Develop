package com.oncha.oncha_web.feature.category.tea_finder;

import org.springframework.web.bind.annotation.GetMapping;

public class TeaFinderController {
    @GetMapping("/finder")
    public String TeaFinder(){return "category/tea_finder";}

}
