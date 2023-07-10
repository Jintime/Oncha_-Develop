package com.oncha.oncha_web.feature.payment.model;

import com.oncha.oncha_web.domain.payment.model.RequsetOnchaPayment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnchaPaymentRequest implements RequsetOnchaPayment {

    private Long id;
    private String impUid;
    private String payment_price;
    private String product_name;
    private String payment_status;
    private String buyer_name;
    private String buyer_email;
    private String post_code;
    private String address;
    private String phone_number;
    private Long seller_id;
}
