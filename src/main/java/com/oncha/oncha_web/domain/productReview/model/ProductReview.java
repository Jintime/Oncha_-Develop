package com.oncha.oncha_web.domain.productReview.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_review")
public class ProductReview {

    @Id
    private Long id;

    @Column(length = 1000)
    private String content;

    private int like;
    private int product_start;


}
