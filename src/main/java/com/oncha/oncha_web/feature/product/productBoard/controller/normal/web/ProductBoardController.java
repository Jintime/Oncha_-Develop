package com.oncha.oncha_web.feature.product.productBoard.controller.normal.web;

import com.oncha.oncha_web.aop.annotation.SetUserInfoInModel;
import com.oncha.oncha_web.feature.product.productBoard.model.ProductBoardRequest;
import com.oncha.oncha_web.feature.product.productBoard.service.ProductBoardService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductBoardController {

    private final ProductBoardService productBoardService;

    @SetUserInfoInModel
    @GetMapping("write")
    public String productWrite(Model model) {
        return "/manager/product/write";
    }

    @SetUserInfoInModel
    @GetMapping("/list")
    public String productList(Model model) {
        return "/manager/product/list";
    }

    @SetUserInfoInModel
    @GetMapping("/edit")
    public String productEdit(Model model) {
        return "/manager/product/edit";
    }

    @SetUserInfoInModel
    @GetMapping("/view")
    public String productView(Model model) {
        return "/manager/product/view";
    }

    @PostMapping("/write")
    public String saveWrite(@ModelAttribute ProductBoardRequest productBoardRequest)
            throws IOException {
        productBoardService.save(productBoardRequest);
        return "index";
    }

}
