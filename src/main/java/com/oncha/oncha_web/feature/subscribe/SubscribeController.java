package com.oncha.oncha_web.feature.subscribe;

import org.springframework.web.bind.annotation.GetMapping;

public class SubscribeController {
    @GetMapping("/subscribe")
    public String Subscribe(){return "/category/subscribe";}
}
