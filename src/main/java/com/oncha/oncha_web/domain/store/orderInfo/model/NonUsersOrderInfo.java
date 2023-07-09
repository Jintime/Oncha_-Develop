package com.oncha.oncha_web.domain.store.orderInfo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nonusers_order_info")
@AllArgsConstructor
@NoArgsConstructor
public class NonUsersOrderInfo extends BaseOrderInfo {
    @Id
    private Long id;

    private String password;

    private String recipientName;
    private String recipientPhoneNumber;

}
