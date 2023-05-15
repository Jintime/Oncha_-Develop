package com.oncha.oncha_web.feature.productBoard.service;

import com.oncha.oncha_web.domain.productBoard.model.ProductBoard;
import com.oncha.oncha_web.domain.productBoard.model.ProductFile;
import com.oncha.oncha_web.domain.productBoard.model.RequestProductBoard;
import com.oncha.oncha_web.domain.productBoard.repository.ProductBoardRepository;
import com.oncha.oncha_web.domain.productBoard.repository.ProductFileRepository;
import com.oncha.oncha_web.feature.productBoard.model.ProductBoardDTO;
import com.oncha.oncha_web.feature.productBoard.model.ProductBoardRequest;
import com.oncha.oncha_web.feature.productBoard.repository.ProductBoardQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
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
    private final ProductBoardQueryRepository productBoardQueryRepository;

    @Transactional
    public void save(ProductBoardRequest productBoardRequest) throws IOException{
        ProductBoard productBoard  = productBoardRepository.save(ProductBoard.toProductBoard(productBoardRequest));

        for(MultipartFile multipartProductFile : productBoardRequest.getProductFile()) {
            String originalFileName = multipartProductFile.getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "" + originalFileName;
            String savePath = System.getProperty("user.dir")+ "\\src\\main\\resources\\static\\file\\" + storedFileName;
            multipartProductFile.transferTo(new File(savePath));

            ProductFile productFile = ProductFile.toProductFile(productBoard, originalFileName, storedFileName);
            productFileRepository.save(productFile);
        }
    }

    @Transactional(readOnly = true)
    public List<ProductBoardDTO> findAll(Pageable pageable) {
        return productBoardQueryRepository.findAllByPageable(pageable).getContent();
    }

    @Transactional(readOnly = true)
    public ProductBoardDTO findById(Long id) {
        return productBoardQueryRepository.findById(id);
    }


}
