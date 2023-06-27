package com.oncha.oncha_web.domain.cart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Cart {

    @Id
    private Long id;

    private Long userId;
    private Long productId;
    
}
