package com.oncha.oncha_web.feature.product.productBoard.model;

import com.oncha.oncha_web.domain.productBoard.model.RequestProductBoard;
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

    private String title;
    private String detail;
    private String productName;
    private String originNation;
    private String type;
    private String flavor;
    private String category;

    private int weight;
    private int price;
    private int productCount;
    private int view;
    private int like;

    private boolean blended;
    private boolean caffeine;
    private boolean allow;

    private List<MultipartFile> productFile;
    private List<String> originFileName;
    private List<String> storedFileName;

}
