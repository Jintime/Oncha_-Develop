package com.oncha.oncha_web.feature.store.controller.normal.web;

import com.oncha.oncha_web.aop.annotation.SetUserInfoInModel;
import com.oncha.oncha_web.feature.product.productBoard.model.ProductBoardDTO;
import com.oncha.oncha_web.feature.product.productBoard.service.ProductBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public String Store(Model model, @PageableDefault(page = 0) Pageable pageable) {
        Page<ProductBoardDTO> productDTOList = productBoardService.paging(pageable);
        int blockLimit = 20; // 페이징 블록 크기
        int currentPage = pageable.getPageNumber(); // 요청한 페이지 번호
        int startPage = (currentPage / blockLimit) * blockLimit + 1;
        int endPage = Math.min(startPage + blockLimit - 1, productDTOList.getTotalPages());

        model.addAttribute("product", productDTOList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

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
