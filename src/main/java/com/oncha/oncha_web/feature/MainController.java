package com.oncha.oncha_web.feature;

import com.oncha.oncha_web.feature.productBoard.model.ProductBoardDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {
    @GetMapping("/index")
    public String Home(){return "index";}

    @GetMapping("/login")
    public String Login(){return "login/loginForm";}


}
