package com.oncha.oncha_web.domain.productBoard.model;

import com.oncha.oncha_web.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
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

    public static ProductFile toProductFile(ProductBoard boardEntity, String originalFileName, String storedFileName) {
        ProductFile boardFileEntity = new ProductFile();
        boardFileEntity.setOriginalFileName(originalFileName);
        boardFileEntity.setStoredFileName(storedFileName);
        boardFileEntity.setProductBoard(boardEntity);
        return boardFileEntity;
    }
}
