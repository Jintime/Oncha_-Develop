package com.oncha.oncha_web.feature.productBoard.service;

import com.oncha.oncha_web.domain.productBoard.model.ProductBoard;
import com.oncha.oncha_web.domain.productBoard.model.ProductFile;
import com.oncha.oncha_web.domain.productBoard.model.RequestProductBoard;
import com.oncha.oncha_web.domain.productBoard.repository.ProductBoardRepository;
import com.oncha.oncha_web.domain.productBoard.repository.ProductFileRepository;
import com.oncha.oncha_web.feature.productBoard.model.ProductBoardDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductBoardService {
    private final ProductBoardRepository productBoardRepository;
    private final ProductFileRepository productFileRepository;

    public void save(ProductBoardDTO productBoardDTO) throws IOException{
        ProductBoard productBoard = ProductBoard.toProductBoard((RequestProductBoard)productBoardDTO);
        Long saveId = productBoardRepository.save(productBoard).getId();
        ProductBoard byId = productBoardRepository.findById(saveId).get();

        for(MultipartFile MultiparProductFile:productBoardDTO.getProductFile()) {
            String originalFileName = MultiparProductFile.getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "" + originalFileName;
            String savePath = System.getProperty("user.dir")+ "\\src\\main\\resources\\static\\file\\" + storedFileName;
            MultiparProductFile.transferTo(new File(savePath));

            ProductFile productFile = ProductFile.toProductFile(byId, originalFileName, storedFileName);
            productFileRepository.save(productFile);
        }
    }

    @Transactional
    public List<ProductBoardDTO> findAll() {
        List<ProductBoard> productList = productBoardRepository.findAll();
        List<ProductBoardDTO> productDTOList =new ArrayList<>();
        for(ProductBoard product : productList){
            productDTOList.add(ProductBoardDTO.toProductBoardDTO(product));
        }
        return productDTOList;
    }
    @Transactional
    public ProductBoardDTO findById(Long id) {
        Optional<ProductBoard> optionalProductEntity = productBoardRepository.findById(id);
        if(optionalProductEntity.isPresent()){
            ProductBoard product =optionalProductEntity.get();
            ProductBoardDTO productDTO = ProductBoardDTO.toProductBoardDTO(product);
            return productDTO;
        }return null;
    }


}
