package com.oncha.oncha_web.feature.product.productBoard.controller.admin.web;

import com.oncha.oncha_web.feature.product.productBoard.model.ProductBoardDTO;
import com.oncha.oncha_web.feature.product.productBoard.service.ProductBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/product_board")
public class AdminProductBoardController {

    private final ProductBoardService productBoardService;

    @GetMapping
    public String viewProductList(Model model, @PageableDefault Pageable pageable){
        List<ProductBoardDTO> productBoardDTOList = productBoardService.findAll(pageable);
        model.addAttribute("productList",productBoardDTOList);
        return "admin/adminProductList";
    }

    // todo : 일단 아래 2개는 옮겨오긴햇는데 쓸건지 말건지는 모르겠음 결정바람
    @GetMapping("/list")
    public String findAll(Model model, @PageableDefault Pageable pageable) {
        List<ProductBoardDTO> productDTOList = productBoardService.findAll(pageable);
        model.addAttribute("productList", productDTOList);
        return "/manager/product/list";
    }

    @GetMapping("/view/{id}")
    public String findById(@PathVariable Long id, Model model) {
        // productService.updateHits(index);
        ProductBoardDTO productDTO = productBoardService.findById(id);
        model.addAttribute("product", productDTO);
        return "/manager/product/view";
    }

}
