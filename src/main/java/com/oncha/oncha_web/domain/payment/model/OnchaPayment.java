package com.oncha.oncha_web.domain.payment.model;

import com.oncha.oncha_web.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "payment_info")
public class OnchaPayment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column
    private String post_code;
    @Column
    private String phone_number;



    @Builder
    public OnchaPayment(Long id, String impUid, String payment_price, String payment_status,
                        String buyer_name, String buyer_email,
                        String post_code,String phone_number){
        this.id =id;
        this.impUid = impUid;
        this.payment_price = payment_price;
        this.payment_status = payment_status;
        this.buyer_name = buyer_name;
        this.buyer_email = buyer_email;
        this.post_code =post_code;
        this.phone_number =phone_number;
    }

    public static OnchaPayment toPayment(RequsetOnchaPayment requsetOnchaPayment){
        return builder().id(requsetOnchaPayment.getId())
                .impUid(requsetOnchaPayment.getImpUid())
                .payment_price(requsetOnchaPayment.getPayment_price())
                .payment_status(requsetOnchaPayment.getPayment_status())
                .buyer_name(requsetOnchaPayment.getBuyer_name())
                .buyer_email(requsetOnchaPayment.getBuyer_email())
                .post_code(requsetOnchaPayment.getPost_code())
                .phone_number(requsetOnchaPayment.getPhone_number())
                .build();
    }
}
