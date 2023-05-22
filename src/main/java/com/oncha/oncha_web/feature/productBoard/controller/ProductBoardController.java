package com.oncha.oncha_web.feature.productBoard.controller;

import com.oncha.oncha_web.feature.productBoard.model.ProductBoardDTO;
import com.oncha.oncha_web.feature.productBoard.model.ProductBoardRequest;
import com.oncha.oncha_web.feature.productBoard.service.ProductBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product_board")
public class ProductBoardController {

    private final ProductBoardService productBoardService;

    @GetMapping("write")
    public String productWrite(){return "product/write";}
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


    @PostMapping("/write")
    public String saveWrite(@ModelAttribute ProductBoardRequest productBoardRequest) throws IOException {
        productBoardService.save(productBoardRequest);
        return "index";
    }

    @GetMapping("/admin")
    public String findAll(Model model, @PageableDefault Pageable pageable){
        List<ProductBoardDTO> productDTOList = productBoardService.findAll(pageable);
        model.addAttribute("productList",productDTOList);
        return "product/list";
    }

    @GetMapping("/admin/{id}")
    public String findById(@PathVariable Long id, Model model){
        // productService.updateHits(index);
        ProductBoardDTO productDTO = productBoardService.findById(id);
        model.addAttribute("product",productDTO);
        return "product/view";
    }

    @GetMapping("/odertest/{id}")
    public String findById2(@PathVariable Long id,Model model){
        // productService.updateHits(index);
        ProductBoardDTO productDTO = productBoardService.findById(id);
        model.addAttribute("product",productDTO);
        return "testorder";
    }
    @GetMapping("/payment/{id}")
    public String oder(@PathVariable Long id, Model model){
        // productService.updateHits(index);
        ProductBoardDTO productDTO = productBoardService.findById(id);
        model.addAttribute("product",productDTO);
        return "testorder";
    }



}
