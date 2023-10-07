package com.oncha.oncha_web.feature;

import com.oncha.oncha_web.aop.annotation.SetUserInfoInModel;
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
    @GetMapping({"/","index"})
    @SetUserInfoInModel
    public String Home(Model model,@PageableDefault Pageable pageable){
        List<ProductBoardDTO> productDTOList = productBoardService.findAll(pageable);
        model.addAttribute("product",productDTOList);
        return "index";
    }

    @SetUserInfoInModel
    @GetMapping("/darakbang")
    public String Dracbang(Model model){return "user/category/teaHouse/darakbang";}


    ///옮겨야될코드들
    @GetMapping("/rank")
    public String Rank(){return "user/store/ranks";}
    @GetMapping("/teaFinder")
    public String teaFinder(){return"user/category/myOncha/teaFinder";}
    @GetMapping("/like")
    public String Likes(){return "user/store/likes";}

    @GetMapping("/test1")
    public String frontTest1(){return "user/store/order/testorder";}

    @GetMapping("/test2")
    public String frontTest2(){return "user/category/myOncha/myPage";}

    @GetMapping("/test3")
    public String frontTest3(){return "user/category/myOncha/onchaCart";}

    @GetMapping("/test4")
    public String frontTest4(){return "user/store/order/testorder";}
}
