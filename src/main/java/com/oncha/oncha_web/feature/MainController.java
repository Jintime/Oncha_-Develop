package com.oncha.oncha_web.feature;

import com.oncha.oncha_web.feature.product.productBoard.model.ProductBoardDTO;
import com.oncha.oncha_web.feature.product.productBoard.service.ProductBoardService;
import com.oncha.oncha_web.feature.user.model.MemberDTO;
import com.oncha.oncha_web.feature.user.service.MemberService;
import com.oncha.oncha_web.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ProductBoardService productBoardService;
    private final MemberService memberService;
    @GetMapping({"/","index"})
    public String Home(Model model,@PageableDefault Pageable pageable){

        List<ProductBoardDTO> productDTOList = productBoardService.findAll(pageable);
        model.addAttribute("product",productDTOList);
        return "index";
    }

    @GetMapping("/darakbang")
    public String Dracbang(){return "user/category/teaHouse/darakbang";}


    ///옮겨야될코드들

    @GetMapping("/rank")
    public String Rank(){return "user/store/ranks";}
    @GetMapping("/teaFinder")
    public String teaFinder(){return"user/category/myOncha/teaFinder";}
    @GetMapping("/like")
    public String Likes(){return "user/store/likes";}


}
