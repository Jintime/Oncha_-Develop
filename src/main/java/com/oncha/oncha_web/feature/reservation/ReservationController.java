package com.oncha.oncha_web.feature.reservation;

import org.springframework.web.bind.annotation.GetMapping;

public class ReservationController {

    @GetMapping("/reservation")
    public String Reservation(){return "category/reservation";}

}
