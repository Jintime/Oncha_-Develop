package com.oncha.oncha_web.feature.payment.controller;

import com.oncha.oncha_web.feature.payment.model.PaymentRequest;
import com.oncha.oncha_web.feature.payment.service.PaymentService;
import com.oncha.oncha_web.feature.productBoard.model.ProductBoardRequest;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Locale;

@Controller
@RequestMapping("/order")
public class PaymentController {

    private IamportClient api;
    private PaymentService paymentService;
    public PaymentController() {
        // REST API 키와 REST API secret 를 아래처럼 순서대로 입력한다.
        this.api = new IamportClient("6332301523447220","s6IZNUNmxgPZ5RJe2dUNsSx7axyeIvL49m8cPyAL91XO1TjXO4YWUiQRBCpJ8hIkAHpngtZuq1zXWA0m");
    }

    @RequestMapping(value="/orderComplete", produces = "application/text; charset=utf8", method = RequestMethod.GET)
    public String orderComplete(
            @RequestParam(required = false) String imp_uid
            , @RequestParam(required = false) String merchant_uid
            , @ModelAttribute PaymentRequest paymentRequest
            , Locale locale
            , HttpSession session) throws IamportResponseException, IOException
    {

        IamportResponse<Payment> result = api.paymentByImpUid(imp_uid);

        // 결제 가격과 검증결과를 비교한다.
        if(result.getResponse().getAmount().compareTo(BigDecimal.valueOf(100)) == 0) {
            paymentService.save(paymentRequest);
            System.out.println("검증통과");
        }

        return "redirect:index";
    }

}
