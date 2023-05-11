package com.oncha.oncha_web.feature.productBoard.model;

import com.oncha.oncha_web.domain.productBoard.model.ProductBoard;
import com.oncha.oncha_web.domain.productBoard.model.ProductFile;
import com.oncha.oncha_web.domain.productBoard.model.RequestProductBoard;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductBoardDTO implements RequestProductBoard {

    private Long id;

    private String title;
    private String detail;
    private String product_name;
    private String origin_nation;
    private String type;
    private String flavor;
    private String category;

    private int weight;
    private int price;
    private int product_count;
    private int view;
    private int like;

    private boolean blended;
    private boolean caffeine;

    private List<MultipartFile> productFile;
    private List<String> originFileName;
    private List<String> storedFileName;

    public static ProductBoardDTO toProductBoardDTO(ProductBoard productBoard){
        ProductBoardDTO productBoardDTO = new ProductBoardDTO();
        productBoardDTO.setId(productBoard.getId());
        productBoardDTO.setTitle(productBoard.getTitle());
        productBoardDTO.setDetail(productBoard.getDetail());
        productBoardDTO.setProduct_name(productBoard.getProduct_name());
        productBoardDTO.setOrigin_nation(productBoard.getOrigin_nation());
        productBoardDTO.setType(productBoard.getType());
        productBoardDTO.setFlavor(productBoard.getFlavor());
        productBoardDTO.setCategory(productBoard.getCategory());
        productBoardDTO.setWeight(productBoard.getWeight());
        productBoardDTO.setPrice(productBoard.getPrice());
        productBoardDTO.setProduct_count(productBoard.getProduct_count());
        productBoardDTO.setView(productBoard.getView());
        productBoardDTO.setLike(productBoard.getLike());
        productBoardDTO.setBlended(productBoard.isBlended());
        productBoardDTO.setCaffeine(productBoard.isCaffeine());

       List<String> originFileNameList =new ArrayList<>();
        List<String> storedFileNameList =new ArrayList<>();
        for(ProductFile productFile:productBoard.getProductFileList()) {
            originFileNameList.add(productFile.getOriginalFileName());
            storedFileNameList.add(productFile.getStoredFileName());
        }
        productBoardDTO.setOriginFileName(originFileNameList);
        productBoardDTO.setStoredFileName(originFileNameList);
        return productBoardDTO;
    }

}
