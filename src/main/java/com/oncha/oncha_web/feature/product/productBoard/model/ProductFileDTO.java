package com.oncha.oncha_web.feature.product.productBoard.model;

import com.oncha.oncha_web.domain.productBoard.model.ProductFile;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductFileDTO {
    private String originFileName;
    private String url;

    @QueryProjection
    public ProductFileDTO (ProductFile productFile) {
        this.originFileName = productFile.getOriginalFileName();
        this.url = productFile.getUrl();
    }
}
