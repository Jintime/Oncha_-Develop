package com.oncha.oncha_web.domain.productBoard.model;

import com.oncha.oncha_web.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


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

    @Column(length = 50)
    private String origin_nation;

    @Column(length = 15)
    private String type;

    @Column(length = 30)
    private String flavor;

    @Column(length = 30)
    private String category;


    private int weight;
    private int price;
    private int product_count;
    private int view;
    private int love;

    private boolean blended;
    private boolean caffeine;

    private boolean allow;

    @OneToMany(mappedBy = "productBoard",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<ProductFile> productFileList= new ArrayList<>();

    public void allowed() {
        this.allow = true;
    }


    @Builder
    public ProductBoard(Long id, String title, String detail, String product_name, String origin_nation,
                        String type, String flavor, String category, int weight, int price, int product_count,
                        int view, int love, boolean blended, boolean caffeine,boolean allow){
        this.id =id;
        this.title = title;
        this.detail = detail;
        this.product_name=product_name;
        this.origin_nation=origin_nation;
        this.type = type;
        this.flavor = flavor;
        this.category = category;
        this.weight = weight;
        this.price =price;
        this.product_count = product_count;
        this.view = view;
        this.love = love;
        this.blended =blended;
        this.caffeine = caffeine;
        this.allow =allow;
    }

    public static ProductBoard toProductBoard(RequestProductBoard requestProductBoard){
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
