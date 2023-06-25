package com.oncha.oncha_web.feature.product.productBoard.model;

import com.oncha.oncha_web.domain.productBoard.model.ProductBoard;
import com.querydsl.core.annotations.QueryProjection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductBoardDTO {

    private Long id;

    private String title;

    private String detail;

    private String product_name;

    private String origin_nation;

    private String type;

    private String flavor;

    private String category;

    private int weight;

    private int price;

    private int product_count;

    private int view;

    private int like;

    private boolean blended;

    private boolean caffeine;

    private boolean allow;

    private List<ProductFileDTO> list;

    public ProductBoardDTO(ProductBoard productBoard, List<ProductFileDTO> dtos) {
        this.id = productBoard.getId();
        this.title = productBoard.getTitle();
        this.detail = productBoard.getDetail();
        this.product_name = productBoard.getProduct_name();
        this.origin_nation = productBoard.getTeaCategory().getOriginNation();
        this.type = productBoard.getTeaCategory().getType();
        this.flavor = productBoard.getTeaCategory().getFlavor();
        this.category = productBoard.getTeaCategory().getCategory();
        this.weight = productBoard.getWeight();
        this.price = productBoard.getPrice();
        this.product_count = productBoard.getProduct_count();
        this.view = productBoard.getView();
        this.like = productBoard.getLove();
        this.blended = productBoard.getTeaCategory().isBlended();
        this.caffeine = productBoard.getTeaCategory().isCaffeine();
        this.allow = productBoard.isAllow();
        this.list = dtos;
    }

    @QueryProjection
    public ProductBoardDTO(ProductBoard productBoard) {
        this.id = productBoard.getId();
        this.title = productBoard.getTitle();
        this.detail = productBoard.getDetail();
        this.product_name = productBoard.getProduct_name();
        this.origin_nation = productBoard.getTeaCategory().getOriginNation();
        this.type = productBoard.getTeaCategory().getType();
        this.flavor = productBoard.getTeaCategory().getFlavor();
        this.category = productBoard.getTeaCategory().getCategory();
        this.weight = productBoard.getWeight();
        this.price = productBoard.getPrice();
        this.product_count = productBoard.getProduct_count();
        this.view = productBoard.getView();
        this.like = productBoard.getLove();
        this.blended = productBoard.getTeaCategory().isBlended();
        this.caffeine = productBoard.getTeaCategory().isCaffeine();
        this.allow = productBoard.isAllow();
        this.list = productBoard.getProductFileList().stream().map(ProductFileDTO::new).toList();
    }
}
