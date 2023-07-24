package com.oncha.oncha_web.feature.product.productCart.service;

import com.oncha.oncha_web.domain.cart.model.ProductCart;
import com.oncha.oncha_web.domain.cart.repository.ProductCartRepository;
import com.oncha.oncha_web.domain.productBoard.model.ProductBoard;
import com.oncha.oncha_web.domain.productBoard.repository.ProductBoardRepository;
import com.oncha.oncha_web.exception._40x.EntityNotFoundException;
import com.oncha.oncha_web.feature.product.productCart.model.ProductCartSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductCartService {

    private final ProductBoardRepository productBoardRepository;

    private final ProductCartRepository productCartRepository;

    @Transactional
    public Long saveProduct(ProductCartSaveRequest request) {
        ProductBoard productBoard = getProductBoardById(request.getProductBoardId());
        ProductCart productCart = productCartRepository.save(new ProductCart(productBoard, request.getAmount()));
        return productCart.getId();
    }

    private ProductBoard getProductBoardById (Long productBoardId) {
        return productBoardRepository.findById(productBoardId).orElseThrow(
            () -> new EntityNotFoundException(productBoardId, ProductBoard.class)
        );
    }
}
