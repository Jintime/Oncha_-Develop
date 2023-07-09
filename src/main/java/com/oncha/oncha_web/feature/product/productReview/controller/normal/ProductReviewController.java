package com.oncha.oncha_web.feature.product.productReview.controller.normal;

import com.oncha.oncha_web.aop.annotation.SetUserInfoInModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ProductReviewController {

    @GetMapping("write")
    public String productWrite(Model model) {
        return "/manager/product/write";
    }

    @GetMapping("/list")
    public String productList(Model model) {
        return "/manager/product/list";
    }

    @GetMapping("/edit")
    public String productEdit(Model model) {
        return "/manager/product/edit";
    }

    @GetMapping("/view")
    public String productView(Model model) {
        return "/manager/product/view";
    }


}
