package com.oncha.oncha_web.feature.store;

import org.springframework.web.bind.annotation.GetMapping;

public class StroeController {

    @GetMapping("/store")
    public String Store(){return "category/store";}
}
