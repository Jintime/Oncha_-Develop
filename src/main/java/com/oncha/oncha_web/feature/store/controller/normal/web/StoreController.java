package com.oncha.oncha_web.feature.store.controller.normal.web;

import com.oncha.oncha_web.aop.annotation.SetUserInfoInModel;
import com.oncha.oncha_web.feature.product.productBoard.model.ProductBoardDTO;
import com.oncha.oncha_web.feature.product.productBoard.service.ProductBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {
    private final ProductBoardService productBoardService;

    @SetUserInfoInModel
    @GetMapping("")
    public String Store(Model model, @PageableDefault(size = 4, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        List<ProductBoardDTO> productDTOList = productBoardService.findAll(pageable);
        model.addAttribute("product",productDTOList);
        return "user/store/store";
    }


    @SetUserInfoInModel
    @GetMapping("/{id}")
    public String ProductInfo(@PathVariable Long id, Model model){
        ProductBoardDTO productDTO = productBoardService.findById(id);
        model.addAttribute("product",productDTO);
        return "user/store/order/testorder";
    }

    @SetUserInfoInModel
    @GetMapping("/order/{id}")
    public String Order(@PathVariable Long id, Model model){
        ProductBoardDTO productDTO = productBoardService.findById(id);
        model.addAttribute("product",productDTO);
        return "user/store/order/order";
    }

    @GetMapping("/order/non_{id}")
    public String NonMemberOrder(@PathVariable Long id, Model model){
        ProductBoardDTO productDTO = productBoardService.findById(id);
        model.addAttribute("product",productDTO);
        return "user/store/order/order";
    }


}
