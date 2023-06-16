package com.oncha.oncha_web.feature.product.productReview.model;

import com.oncha.oncha_web.domain.productReview.model.RequestProductReview;
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
