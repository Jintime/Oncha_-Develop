package com.oncha.oncha_web.domain.store.orderInfo.model;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public class BaseOrderInfo {

    private Long productId;

    private String buyerName;
    private String buyerPhoneNumber;
    private String buyerEmail;

    private String zipcode;
    private String address;
    private String address_detail;

    private String request;
}
