package com.oncha.oncha_web.feature.product.productCart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCartSaveRequest {

    private Long productBoardId;

    private Integer amount;
}
