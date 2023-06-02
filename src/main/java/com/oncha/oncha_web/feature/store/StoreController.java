package com.oncha.oncha_web.feature.store;

import com.oncha.oncha_web.feature.productBoard.model.ProductBoardDTO;
import com.oncha.oncha_web.feature.productBoard.service.ProductBoardService;
import com.oncha.oncha_web.feature.user.model.MemberDTO;
import com.oncha.oncha_web.feature.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {
    private final ProductBoardService productBoardService;
    private final MemberService memberService;

    @GetMapping("")
    public String Store(){return "store/store";}

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
        ProductBoardDTO productDTO = productBoardService.findById(id);
        model.addAttribute("product",productDTO);
        return "store/order/order";
    }

}
