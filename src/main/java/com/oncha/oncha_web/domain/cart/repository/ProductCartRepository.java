package com.oncha.oncha_web.domain.cart.repository;

import com.oncha.oncha_web.domain.cart.model.ProductCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCartRepository extends JpaRepository<ProductCart, Long> {

    @EntityGraph(attributePaths = {"productBoard"})
    Page<ProductCart> findAllByCreatedBy(Long id, Pageable pageable);
}
