package com.oncha.oncha_web.feature.payment.controller;


import com.oncha.oncha_web.feature.payment.service.PaymentService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class PaymentController {

  /*  private PaymentService paymentService;

    @RequestMapping("/payment/callback_receive")
    public ResponseEntity<?> callback_receive(@RequestBody Map<String, Object> model) throws JSONException {
        String process_result="결제성공!";
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type","application/json; charset=UTF-8");
        JSONObject responseObj = new JSONObject();

        try{
            String imp_uid = (String) model.get("imp_uid");
            String merchant_uid = (String) model.get("merchant_uid");
            boolean success = (Boolean)model.get("success");
            String error_msg =(String)model.get("error_msg");

            System.out.println("----callback-----");
            System.out.println("-------------------");
            System.out.println("imp_uid : "+imp_uid);
            System.out.println(("merchant_uid : " + merchant_uid));
            System.out.println("success : "+success);

            if(success==true){
                String api_key = "6332301523447220";
                String api_secret = "s6IZNUNmxgPZ5RJe2dUNsSx7axyeIvL49m8cPyAL91XO1TjXO4YWUiQRBCpJ8hIkAHpngtZuq1zXWA0m";

                IamportClient ic = new IamportClient(api_key,api_secret);
                IamportResponse<Payment> response =ic.paymentByImpUid(imp_uid);

                BigDecimal iamport_amount = response.getResponse().getAmount();

                responseObj.put("process_result","결제성공");
            }else{
                System.out.println("error_msg : "+error_msg);
                responseObj.put("process_result","결제실패 :"+error_msg );
            }
        }catch (Exception e){
            e.printStackTrace();
            responseObj.put("process_result","결제실패 : 관리자에게 문의");
        }
        return  new ResponseEntity<String>(responseObj.toString(),responseHeaders, HttpStatus.OK);


    }

*/


}
