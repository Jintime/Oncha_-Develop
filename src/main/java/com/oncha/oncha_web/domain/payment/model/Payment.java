package com.oncha.oncha_web.domain.payment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "payment_info")
public class Payment {

    @Id
    private Long id;

    @Column
    private String impUid;
    @Column
    private String payment_price;
    @Column
    private String payment_status;
    @Column
    private String buyer_name;
    @Column
    private String buyer_email;

    @Builder
    public Payment(Long id, String impUid,String payment_price,String payment_status,String buyer_name,String buyer_email){
        this.id =id;
        this.impUid = impUid;
        this.payment_price = payment_price;
        this.payment_status = payment_status;
        this.buyer_name = buyer_name;
        this.buyer_email = buyer_email;
    }

    public static Payment toPayment(RequsetPayment requsetPayment){
        return builder().id(requsetPayment.getId())
                .impUid(requsetPayment.getImpUid())
                .payment_price(requsetPayment.getPayment_price())
                .payment_status(requsetPayment.getPayment_status())
                .buyer_name(requsetPayment.getBuyer_name())
                .buyer_email(requsetPayment.getBuyer_email())
                .build();
    }
}
