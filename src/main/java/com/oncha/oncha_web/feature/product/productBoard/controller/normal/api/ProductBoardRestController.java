package com.oncha.oncha_web.feature.product.productBoard.controller.normal.api;

import com.oncha.oncha_web.feature.product.productBoard.model.ProductBoardDTO;
import com.oncha.oncha_web.feature.product.productBoard.service.ProductBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductBoardRestController {

    private final ProductBoardService productBoardService;


    @GetMapping("/getId/{id}")
    public ProductBoardDTO GetId(@PathVariable Long id) {
        ProductBoardDTO productBoardDTO = productBoardService.findById(id);
        return productBoardDTO;
    }
}
