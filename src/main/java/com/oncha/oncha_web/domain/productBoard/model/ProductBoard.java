package com.oncha.oncha_web.domain.productBoard.model;

import com.oncha.oncha_web.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "product_board")
public class ProductBoard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private Long companyId;

    @Column(length = 200)
    private String title;

    @Column(length = 500)
    private String detail;

    @Column(length = 200)
    private String productName;

    @Embedded
    private TeaCategory teaCategory;

    private int view;

    private Integer weight;
    private Integer price;
    private Integer productCount;

    @OneToMany(mappedBy = "productBoard", fetch = FetchType.LAZY)
    private List<ProductBoardLike> productBoardLikes;

    @OneToMany(mappedBy = "productBoard", cascade = CascadeType.REMOVE,orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductFile> productFiles;

    public ProductBoard (RequestProductBoard requestProductBoard){
        this.companyId = requestProductBoard.getCompanyId();
        this.title = requestProductBoard.getTitle();
        this.detail = requestProductBoard.getDetail();
        this.productName = requestProductBoard.getProductName();
        this.weight = requestProductBoard.getWeight();
        this.price = requestProductBoard.getPrice();
        this.productCount = requestProductBoard.getProductCount();
        this.teaCategory = requestProductBoard.getTeaCategory();
        this.view = 0;
    }

    public static ProductBoard toProductBoard(RequestProductBoard requestProductBoard){
        return new ProductBoard(requestProductBoard);
    }


}
