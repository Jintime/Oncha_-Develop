package com.oncha.oncha_web.feature;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/index")
    public String Home(){return "index_test";}
    @GetMapping("/finder")
    public String TeaFinder(){return "category/tea_finder";}
    @GetMapping("/store")
    public String Store(){return "category/store";}
    @GetMapping("/reservation")
    public String Reservation(){return "category/reservation";}
    @GetMapping("/subscribe")
    public String Subscribe(){return "/category/subscribe";}

}
