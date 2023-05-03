package com.oncha.oncha_web.feature.productBoard.controller;

import com.oncha.oncha_web.feature.productBoard.service.ProductBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product_board")
public class ProductBoardController {

    private final ProductBoardService productBoardService;

    @GetMapping("write")
    public String productWrite(){return "product/wirte";}
    @GetMapping("/list")
    public String productList(){
        return "product/list";
    }
    @GetMapping("/edit")
    public String productEdit(){
        return "product/edit";
    }
    @GetMapping("/view")
    public String productView(){
        return "product/view";
    }




}
