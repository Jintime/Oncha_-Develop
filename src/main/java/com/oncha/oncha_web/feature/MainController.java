package com.oncha.oncha_web.feature;

import com.oncha.oncha_web.feature.productBoard.model.ProductBoardDTO;
import com.oncha.oncha_web.feature.productBoard.model.ProductBoardRequest;
import com.oncha.oncha_web.feature.productBoard.service.ProductBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ProductBoardService productBoardService;

    @GetMapping("/index")
    public String Home(Model model){
       // List<ProductBoardDTO> productDTOList = productBoardService.findAll();
        //model.addAttribute("productList",productDTOList);
        return "index";}

    @GetMapping("/login")
    public String Login(){return "login/loginForm";}


}
