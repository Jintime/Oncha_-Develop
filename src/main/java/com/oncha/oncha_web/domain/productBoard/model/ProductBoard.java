package com.oncha.oncha_web.domain.productBoard.model;

import com.oncha.oncha_web.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import org.hibernate.annotations.SQLDelete;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "product_board")
@SQLDelete(sql = "update product_board set deleted_at = now() where id = ?")
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
    private String product_name;

    @Column(length = 50)
    private String origin_nation;

    @Column(length = 15)
    private String type;

    @Column(length = 30)
    private String flavor;

    @Column(length = 30)
    private String category;

    @Column(length = 50)
    private String blended;

    private Integer weight;
    private Integer price;
    private Integer product_count;
    private Integer view;
    private Integer love;
    private Boolean caffeine;

    @OneToMany(mappedBy = "productBoard",cascade = CascadeType.REMOVE,orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductFile> productFiles;

    @Builder
    public ProductBoard(Long id,long companyId, String title, String detail, String product_name, String origin_nation,
                        String type, String flavor, String category, int weight, int price, int product_count,
                        int view, int love, String blended, boolean caffeine,boolean allow){
        this.id =id;
        this.companyId = companyId;
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
    }

    public static ProductBoard toProductBoard(RequestProductBoard requestProductBoard){
        return ProductBoard.builder()
                .id(requestProductBoard.getId())
                .companyId(requestProductBoard.getCompanyId())
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
                .blended(requestProductBoard.getBlended())
                .caffeine(requestProductBoard.isCaffeine())
                .build();
    }

}
