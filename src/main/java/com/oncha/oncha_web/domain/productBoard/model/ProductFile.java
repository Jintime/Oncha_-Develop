package com.oncha.oncha_web.domain.productBoard.model;

import com.oncha.oncha_web.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="product_file")
public class ProductFile extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;
    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductBoard productBoard;

    public static ProductFile toProductFile(ProductBoard productBoard,String originalFileName,String storedFileName){
        return new ProductFile(null,originalFileName,storedFileName,productBoard);

    }
}
