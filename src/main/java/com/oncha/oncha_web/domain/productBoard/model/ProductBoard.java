package com.oncha.oncha_web.domain.productBoard.model;

import com.oncha.oncha_web.domain.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
@Table(name = "product_board")
public class ProductBoard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String title;

    @Column(length = 500)
    private String detail;

    @Column(length = 200)
    private String product_name;

    @Embedded
    private TeaCategory teaCategory;

    private int weight;

    private int price;

    private int product_count;

    private int view;

    private int love;

    private boolean allow;

    private boolean recommend;

    @OneToMany(mappedBy = "productBoard", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductFile> productFileList = new ArrayList<>();

    public void allowed() {
        this.allow = true;
    }

    public void enableRecommend() {
        recommend = true;
    }

    public void disableRecommend() {
        recommend = false;
    }

    @Builder
    public ProductBoard(Long id, String title, String detail, String product_name,
        String origin_nation,
        String type, String flavor, String category, int weight, int price, int product_count,
        int view, int love, boolean blended, boolean caffeine, boolean allow) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.product_name = product_name;
        this.teaCategory = new TeaCategory(
            origin_nation,
            type,
            flavor,
            blended,
            caffeine,
            category
        );
        this.weight = weight;
        this.price = price;
        this.product_count = product_count;
        this.view = view;
        this.love = love;
        this.allow = allow;
        this.recommend = false;
    }

    public static ProductBoard toProductBoard(RequestProductBoard requestProductBoard) {
        return ProductBoard.builder()
            .id(requestProductBoard.getId())
            .title(requestProductBoard.getTitle())
            .detail(requestProductBoard.getDetail())
            .product_name(requestProductBoard.getProduct_name())
            .origin_nation(requestProductBoard.getOrigin_nation())
            .type(requestProductBoard.getType())
            .flavor(requestProductBoard.getFlavor())
            .category(requestProductBoard.getCategory())
            .weight(requestProductBoard.getWeight())
            .price(requestProductBoard.getPrice())
            .product_count(requestProductBoard.getProduct_count())
            .view(requestProductBoard.getView())
            .love(requestProductBoard.getLike())
            .blended(requestProductBoard.isBlended())
            .caffeine(requestProductBoard.isCaffeine())
            .allow(requestProductBoard.isAllow())
            .build();
    }


}
