package com.oncha.oncha_web.feature.product.productCart.model;

import com.oncha.oncha_web.domain.cart.model.ProductCart;
import com.oncha.oncha_web.domain.productBoard.model.ProductBoard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCartDTO {

    private Long productCardId;

    private Long productBoardId;

    private String productName;

    private String origin_nation;

    private String type;

    private String flavor;

    private String category;

    private String blended;

    private Integer amount;

    private Integer price;

    private long totalMoney;

    /**
     *
     * entity graph를 이용한 productBoard까지 조회했을 경우 사용하시오
     * @param productCart
     * @return
     */
    public static ProductCartDTO of (ProductCart productCart) {
        ProductBoard productBoard = productCart.getProductBoard();

        return new ProductCartDTO (
            productCart.getId(),
            productBoard.getId(),
            productBoard.getProduct_name(),
            productBoard.getOrigin_nation(),
            productBoard.getType(),
            productBoard.getFlavor(),
            productBoard.getCategory(),
            productBoard.getBlended(),
            productCart.getAmount(),
            productBoard.getPrice(),
            (long) productBoard.getPrice() * productCart.getAmount()
        );
    }
}
