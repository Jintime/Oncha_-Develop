package com.oncha.oncha_web.feature.reservation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationController {

    @GetMapping("/teaHouse")
    public String Reservation(){return "category/teaHouse/teaHouse";}

}
