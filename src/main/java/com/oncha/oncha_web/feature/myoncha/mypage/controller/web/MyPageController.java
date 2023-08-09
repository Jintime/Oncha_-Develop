package com.oncha.oncha_web.feature.myoncha.mypage.controller.web;

import com.oncha.oncha_web.aop.annotation.SetUserInfoInModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MyPageController {

    @SetUserInfoInModel
    @GetMapping("/mypage")
    public String myPage(Model model){
        return "user/category/myOncha/mypage";
    }

    @GetMapping("/detail")
    public String MyPageDetail(){return "user/category/myOncha/MyPageDetail";}
    @GetMapping("/etc")
    public String ETC(){return "user/category/cs/etc";}
    @GetMapping("/orderInfo")
    public String DeliveryTracking(){return "user/category/cs/deliveryTracking";}
    @GetMapping("/refundDetail")
    public String RefundDetail(){return "user/category/cs/refundDetail";}
    @GetMapping("/CS")
    public String CsMain(){return "user/category/cs/csMain";}
}
