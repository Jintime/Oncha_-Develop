package com.oncha.oncha_web.feature.product.productBoard.model;

import com.oncha.oncha_web.domain.productBoard.model.ProductBoard;
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

    private Long companyId;

    private String title;

    private String detail;

    private String product_name;

    private String origin_nation;

    private String type;

    private String flavor;

    private String category;

    private String blended;

    private int weight;

    private int price;

    private int product_count;

    private int view;

    private int like;

    private boolean caffeine;

    private List<ProductFileDTO> list;

    public ProductBoardDTO(ProductBoard productBoard, List<ProductFileDTO> dtos) {
        this.id = productBoard.getId();
        this.companyId = productBoard.getCompanyId();
        this.title = productBoard.getTitle();
        this.detail = productBoard.getDetail();
        this.product_name = productBoard.getProduct_name();
        this.origin_nation = productBoard.getOrigin_nation();
        this.type = productBoard.getType();
        this.flavor = productBoard.getFlavor();
        this.category = productBoard.getCategory();
        this.weight = productBoard.getWeight();
        this.price = productBoard.getPrice();
        this.product_count = productBoard.getProduct_count();
        this.view = productBoard.getView();
        this.like = productBoard.getLove();
        this.blended = productBoard.getBlended();
        this.caffeine = productBoard.getCaffeine();
        this.list = dtos;
    }

    public ProductBoardDTO(ProductBoard productBoard) {
        this.id = productBoard.getId();
        this.companyId = productBoard.getCompanyId();
        this.title = productBoard.getTitle();
        this.detail = productBoard.getDetail();
        this.product_name = productBoard.getProduct_name();
        this.origin_nation = productBoard.getOrigin_nation();
        this.type = productBoard.getType();
        this.flavor = productBoard.getFlavor();
        this.category = productBoard.getCategory();
        this.weight = productBoard.getWeight();
        this.price = productBoard.getPrice();
        this.product_count = productBoard.getProduct_count();
        this.view = productBoard.getView();
        this.like = productBoard.getLove();
        this.blended = productBoard.getBlended();
        this.caffeine = productBoard.getCaffeine();
        this.list = productBoard.getProductFiles().stream().map(ProductFileDTO::new).toList();
    }
}
