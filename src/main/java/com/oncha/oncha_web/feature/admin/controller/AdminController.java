package com.oncha.oncha_web.feature.admin.controller;

import com.oncha.oncha_web.feature.productBoard.model.ProductBoardDTO;
import com.oncha.oncha_web.feature.productBoard.service.ProductBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/on")
public class AdminController {

    private final ProductBoardService productBoardService;
    @GetMapping("list")
    public String viewProductList(Model model, @PageableDefault Pageable pageable){
        List<ProductBoardDTO> productBoardDTOList = productBoardService.findAll(pageable);
        model.addAttribute("productList",productBoardDTOList);
        return "admin/adminProductList";
    }

}
