package com.oncha.oncha_web.domain.store.cart.repository;

import com.oncha.oncha_web.domain.store.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {

}
