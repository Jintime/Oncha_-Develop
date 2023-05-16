package com.oncha.oncha_web.feature.payment.model;

import com.oncha.oncha_web.domain.payment.model.RequsetPayment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest implements RequsetPayment {

    private Long id;
    private String impUid;
    private String payment_price;
    private String payment_status;
    private String buyer_name;
    private String buyer_email;
}
