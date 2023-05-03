package com.oncha.oncha_web.domain.productReview.repository;

import com.oncha.oncha_web.domain.productReview.model.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewRepository extends JpaRepository<ProductReview,Long> {
}
