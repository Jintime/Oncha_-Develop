package com.oncha.oncha_web.feature.store;

import com.oncha.oncha_web.feature.productBoard.model.ProductBoardDTO;
import com.oncha.oncha_web.feature.productBoard.service.ProductBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/Store")
public class StoreController {
    private final ProductBoardService productBoardService;
    @GetMapping("/store")
    public String Store(){return "category/store";}

    @GetMapping("/order/{id}")
    public String findById2(@PathVariable Long id, Model model){
        // productService.updateHits(index);
        ProductBoardDTO productDTO = productBoardService.findById(id);
        model.addAttribute("product",productDTO);
        return "testorder";
    }

}