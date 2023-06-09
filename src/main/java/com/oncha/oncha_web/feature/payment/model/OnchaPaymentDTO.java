package com.oncha.oncha_web.feature.payment.model;


import com.oncha.oncha_web.domain.payment.model.RequsetOnchaPayment;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OnchaPaymentDTO implements RequsetOnchaPayment {

    private Long id;
    private String impUid;
    private String payment_price;
    private String payment_status;
    private String buyer_name;
    private String buyer_email;
    private String post_code;
    private String phone_number;

}
