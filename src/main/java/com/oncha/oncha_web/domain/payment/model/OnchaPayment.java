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
    private String product_name;
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
    private String address;
    @Column
    private String phone_number;
    @Column
    private Long seller_id;



    @Builder
    public OnchaPayment(Long id, String impUid, String payment_price,String product_name,
                        String payment_status, String buyer_name, String buyer_email,
                        String post_code,String address,String phone_number,Long seller_id){
        this.id = id;
        this.impUid = impUid;
        this.product_name = product_name;
        this.payment_price = payment_price;
        this.payment_status = payment_status;
        this.buyer_name = buyer_name;
        this.buyer_email = buyer_email;
        this.post_code = post_code;
        this.address = address;
        this.phone_number = phone_number;
        this.seller_id = seller_id;
    }

    public static OnchaPayment toPayment(RequsetOnchaPayment requsetOnchaPayment){
        return builder().id(requsetOnchaPayment.getId())
                .impUid(requsetOnchaPayment.getImpUid())
                .payment_price(requsetOnchaPayment.getPayment_price())
                .product_name(requsetOnchaPayment.getProduct_name())
                .payment_status(requsetOnchaPayment.getPayment_status())
                .buyer_name(requsetOnchaPayment.getBuyer_name())
                .buyer_email(requsetOnchaPayment.getBuyer_email())
                .post_code(requsetOnchaPayment.getPost_code())
                .address(requsetOnchaPayment.getAddress())
                .phone_number(requsetOnchaPayment.getPhone_number())
                .seller_id(requsetOnchaPayment.getSeller_id())
                .build();
    }
}
