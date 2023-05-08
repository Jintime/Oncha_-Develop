package com.oncha.oncha_web.feature.payment.service;

import com.oncha.oncha_web.domain.payment.PaymentRepository.PaymentRepository;
import com.siot.IamportRestClient.IamportClient;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;
    private IamportClient api;

    public PaymentService() {
        // REST API 키와 REST API secret 를 아래처럼 순서대로 입력한다.
        this.api = new IamportClient("6332301523447220",
                "s6IZNUNmxgPZ5RJe2dUNsSx7axyeIvL49m8cPyAL91XO1TjXO4YWUiQRBCpJ8hIkAHpngtZuq1zXWA0m");
    }





}
