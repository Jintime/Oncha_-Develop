package com.oncha.oncha_web.domain.productReview.model;

import com.oncha.oncha_web.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_review")
public class ProductReview extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String content;

    private int love;
    private int product_star;


}
