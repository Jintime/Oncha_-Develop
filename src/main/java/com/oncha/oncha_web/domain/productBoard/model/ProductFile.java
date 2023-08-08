package com.oncha.oncha_web.domain.productBoard.model;

import com.oncha.oncha_web.domain.BaseEntity;
import com.oncha.oncha_web.domain.file.model.FileInfo;
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

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private FileInfo fileInfo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private ProductBoard productBoard;

    public ProductFile (FileInfo fileInfo, ProductBoard productBoard){
        this.fileInfo = fileInfo;
        this.productBoard = productBoard;
    }
}
