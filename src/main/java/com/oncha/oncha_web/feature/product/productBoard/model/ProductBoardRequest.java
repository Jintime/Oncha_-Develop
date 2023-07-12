package com.oncha.oncha_web.feature.product.productBoard.model;

import com.oncha.oncha_web.domain.productBoard.model.RequestProductBoard;
import com.oncha.oncha_web.domain.productBoard.model.TeaCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductBoardRequest implements RequestProductBoard {

    private Long id;
    private Long companyId;

    private String title;
    private String detail;
    private String productName;

    private TeaCategory teaCategory;

    private Integer weight;
    private Integer price;
    private Integer productCount;

    private List<MultipartFile> productFile;
    private List<String> originFileName;
    private List<String> storedFileName;

}
