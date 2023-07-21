package com.oncha.oncha_web.feature.user.controller.web;

import com.oncha.oncha_web.feature.user.model.AddressDTO;
import com.oncha.oncha_web.feature.user.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AddressRestController {

    private final AddressService addressService;
    @PostMapping("/SaveAddress")
    public String SaveAddress(@ModelAttribute AddressDTO addressDTO){
        addressService.SaveAddress(addressDTO);
        return "redirect:/user/category/myOncha/mypage";
    }
}
