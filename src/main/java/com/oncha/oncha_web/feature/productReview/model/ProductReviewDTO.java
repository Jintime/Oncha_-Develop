package com.oncha.oncha_web.feature.productReview.model;

import com.oncha.oncha_web.domain.productReview.model.RequestProductReview;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductReviewDTO implements RequestProductReview {


    private Long id;

    private String title;
    private String content;

    private int like;
    private int product_star;


}
