package com.oncha.oncha_web.feature.productBoard.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.oncha.oncha_web.domain.productBoard.model.ProductBoard;
import com.oncha.oncha_web.domain.productBoard.model.ProductFile;
import com.oncha.oncha_web.domain.productBoard.model.RequestProductBoard;
import com.oncha.oncha_web.domain.productBoard.repository.ProductBoardRepository;
import com.oncha.oncha_web.domain.productBoard.repository.ProductFileRepository;
import com.oncha.oncha_web.feature.productBoard.model.ProductBoardDTO;
import com.oncha.oncha_web.feature.productBoard.model.ProductBoardRequest;
import com.oncha.oncha_web.feature.productBoard.repository.ProductBoardQueryRepository;
import com.oncha.oncha_web.s3.AmazonS3Service;
import com.oncha.oncha_web.s3.S3FileDto;
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
    private final AmazonS3Service amazonS3Service;

    @Transactional
    public void save(ProductBoardRequest productBoardRequest) throws IOException {
        ProductBoard productBoard  = productBoardRepository.save(ProductBoard.toProductBoard(productBoardRequest));

        List<S3FileDto> s3FileDtoList = amazonS3Service.uploadFiles(productBoardRequest.getProductFile());
        for(S3FileDto s3FileDto : s3FileDtoList) {
            ProductFile productFile = ProductFile.toProductFile(s3FileDto.getOriginalFileName(), s3FileDto.getUploadFilePath(),s3FileDto.getUploadFileUrl(), productBoard);
            productFileRepository.save(productFile);
        }
    }
    @Transactional
    public void allow(Long[] id) throws IOException{
        for (Long productId : id) {
            ProductBoard productBoard = productBoardRepository.findById(productId)
                    .orElseThrow(() -> new NotFoundException("해당 정보가 없습니다"));
            ///추후 수정이 필요할거같음
            productBoard.allowed();
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
