package com.oncha.oncha_web.feature.payment.model;

import com.oncha.oncha_web.domain.payment.model.RequsetOnchaPayment;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OnchaPaymentInfoDTO {

    private String payment_price;
    private String product_name;
    private String buyer_name;
    private String buyer_email;
    private String phone_number;
    private Long seller_id;

}
