package com.oncha.oncha_web.feature.product.store.controller.web;

import com.oncha.oncha_web.feature.product.productBoard.model.ProductBoardDTO;
import com.oncha.oncha_web.feature.product.productBoard.service.ProductBoardService;
import com.oncha.oncha_web.feature.user.model.MemberDTO;
import com.oncha.oncha_web.feature.user.service.MemberService;
import com.oncha.oncha_web.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {
    private final ProductBoardService productBoardService;
    private final MemberService memberService;

    @GetMapping("")
    public String Store(){return "user/store/store";}

    @GetMapping("/{id}")
    public String findById2(@PathVariable Long id, Model model){
        // productService.updateHits(index);
        ProductBoardDTO productDTO = productBoardService.findById(id);
        model.addAttribute("product",productDTO);
        return "testorder";
    }

    @GetMapping("/order/{id}")
    public String findById(@PathVariable Long id, Model model){
        // productService.updateHits(index);
        Optional<Long> userId = SecurityUtil.getCurrentId();
        ProductBoardDTO productDTO = productBoardService.findById(id);
        MemberDTO memberDTO = memberService.findById(userId.get());
        model.addAttribute("product",productDTO);
        model.addAttribute("user",memberDTO);
        return "user/store/order/order";
    }

}
