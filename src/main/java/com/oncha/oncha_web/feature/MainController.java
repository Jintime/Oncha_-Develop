package com.oncha.oncha_web.feature;

import com.oncha.oncha_web.feature.product.productBoard.model.ProductBoardDTO;
import com.oncha.oncha_web.feature.product.productBoard.service.ProductBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ProductBoardService productBoardService;

    @GetMapping("/")
    public String Home(Model model,@PageableDefault Pageable pageable){
       List<ProductBoardDTO> productDTOList = productBoardService.findAll(pageable);
        model.addAttribute("product",productDTOList);
        return "index";
    }
}
