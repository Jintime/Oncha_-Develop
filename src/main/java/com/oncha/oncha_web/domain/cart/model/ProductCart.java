package com.oncha.oncha_web.domain.cart.model;

import com.oncha.oncha_web.domain.BaseEntity;
import com.oncha.oncha_web.domain.productBoard.model.ProductBoard;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductCart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ProductBoard productBoard;

    private Integer amount;

    public ProductCart (ProductBoard productBoard, Integer amount) {
        this.productBoard = productBoard;
        this.amount = amount;
    }
}
