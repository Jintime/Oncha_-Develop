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

    private String productName;

    private String originNation;

    private String type;

    private String flavor;

    private String blending;

    private Integer weight;

    private Integer price;

    private Integer productCount;

    private Integer view;

    private Integer like;

    private Boolean caffeine;

    private boolean allow;

    private List<ProductFileDTO> list;

    public ProductBoardDTO(ProductBoard productBoard, List<ProductFileDTO> dtos) {
        this.id = productBoard.getId();
        this.companyId = productBoard.getCompanyId();
        this.title = productBoard.getTitle();
        this.detail = productBoard.getDetail();
        this.productName = productBoard.getProductName();
        this.originNation = productBoard.getTeaCategory().getOriginNation();
        this.type = productBoard.getTeaCategory().getTeaType();
        this.flavor = productBoard.getTeaCategory().getFlavor();
        this.weight = productBoard.getWeight();
        this.price = productBoard.getPrice();
        this.productCount = productBoard.getProductCount();
        this.view = productBoard.getView();
        this.like = productBoard.getProductBoardLikes().size();
        this.blending = productBoard.getTeaCategory().getBlending();
        this.caffeine = productBoard.getTeaCategory().getCaffeine();
        this.list = dtos;
    }

    public ProductBoardDTO(ProductBoard productBoard) {
        this.id = productBoard.getId();
        this.companyId = productBoard.getCompanyId();
        this.title = productBoard.getTitle();
        this.detail = productBoard.getDetail();
        this.productName = productBoard.getProductName();
        this.originNation = productBoard.getTeaCategory().getOriginNation();
        this.type = productBoard.getTeaCategory().getTeaType();
        this.flavor = productBoard.getTeaCategory().getFlavor();
        this.weight = productBoard.getWeight();
        this.price = productBoard.getPrice();
        this.productCount = productBoard.getProductCount();
        this.view = productBoard.getView();
        this.like = productBoard.getProductBoardLikes().size();
        this.blending = productBoard.getTeaCategory().getBlending();
        this.caffeine = productBoard.getTeaCategory().getCaffeine();
        this.list = productBoard.getProductFiles().stream().map(ProductFileDTO::new).toList();
    }
}
