package com.oncha.oncha_web.feature.store.controller.normal.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StoreRestController {

    @PostMapping("/saveAddress")
    public String SaveAddress(){
        return "redirect:user/store/store";
    }

}
