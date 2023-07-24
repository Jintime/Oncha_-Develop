package com.oncha.oncha_web.domain.cart.repository;

import com.oncha.oncha_web.domain.cart.model.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCartRepository extends JpaRepository<ProductCart, Long> {

}
