package com.oncha.oncha_web.feature.product.productBoard.controller.normal.web;

import com.oncha.oncha_web.feature.product.productBoard.model.ProductBoardRequest;
import com.oncha.oncha_web.feature.product.productBoard.service.ProductBoardService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product_board")
public class ProductBoardController {

    private final ProductBoardService productBoardService;

    @GetMapping("write")
    public String productWrite() {
        return "/manager/product/write";
    }

    @GetMapping("/list")
    public String productList() {
        return "/manager/product/list";
    }

    @GetMapping("/edit")
    public String productEdit() {
        return "/manager/product/edit";
    }

    @GetMapping("/view")
    public String productView() {
        return "/manager/product/view";
    }

    @PostMapping("/write")
    public String saveWrite(@ModelAttribute ProductBoardRequest productBoardRequest)
        throws IOException {
        productBoardService.save(productBoardRequest);
        return "index";
    }

}
