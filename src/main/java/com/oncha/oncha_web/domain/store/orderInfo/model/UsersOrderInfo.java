package com.oncha.oncha_web.domain.store.orderInfo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users_orders_info")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UsersOrderInfo extends BaseOrderInfo {
    @Id
    private Long id;

}
