package com.oncha.oncha_web.feature.product.productBoard.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.oncha.oncha_web.domain.productBoard.model.ProductBoard;
import com.oncha.oncha_web.domain.productBoard.model.ProductFile;
import com.oncha.oncha_web.domain.productBoard.repository.ProductBoardRepository;
import com.oncha.oncha_web.domain.productBoard.repository.ProductFileRepository;
import com.oncha.oncha_web.exception._40x.EntityNotFoundException;
import com.oncha.oncha_web.feature.product.productBoard.model.ProductBoardRequest;
import com.oncha.oncha_web.feature.product.productBoard.model.ProductBoardDTO;
import com.oncha.oncha_web.feature.product.productBoard.repository.ProductBoardQueryRepository;
import com.oncha.oncha_web.feature.s3.AmazonS3Service;
import com.oncha.oncha_web.feature.s3.S3FileDto;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductBoardService {

    private final ProductBoardRepository productBoardRepository;

    private final ProductFileRepository productFileRepository;

    private final ProductBoardQueryRepository productBoardQueryRepository;

    private final AmazonS3Service amazonS3Service;

    @Transactional
    public void save(ProductBoardRequest productBoardRequest) throws IOException {
        ProductBoard productBoard = productBoardRepository.save(
            ProductBoard.toProductBoard(productBoardRequest));

        List<S3FileDto> s3FileDtoList = amazonS3Service.uploadFiles(
            productBoardRequest.getProductFile());
        for (S3FileDto s3FileDto : s3FileDtoList) {
            ProductFile productFile = ProductFile.toProductFile(s3FileDto.getOriginalFileName(),
                s3FileDto.getUploadFilePath(), s3FileDto.getUploadFileUrl(), productBoard);
            productFileRepository.save(productFile);
        }
    }


    @Transactional(readOnly = true)
    public List<ProductBoardDTO> findAll(Pageable pageable) {
        return productBoardQueryRepository
            .findAllByPageable(pageable).getContent().stream().map(ProductBoardDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public ProductBoardDTO findById(Long id) {
        return new ProductBoardDTO(productBoardQueryRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException(id, ProductBoard.class)
        ));
    }


}
