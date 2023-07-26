package com.oncha.oncha_web.feature.product.productCart.service;

import com.oncha.oncha_web.domain.cart.model.ProductCart;
import com.oncha.oncha_web.domain.cart.repository.ProductCartRepository;
import com.oncha.oncha_web.domain.productBoard.model.ProductBoard;
import com.oncha.oncha_web.domain.productBoard.repository.ProductBoardRepository;
import com.oncha.oncha_web.exception._40x.AccessDinedException;
import com.oncha.oncha_web.exception._40x.EntityNotFoundException;
import com.oncha.oncha_web.feature.product.productCart.model.ProductCartDTO;
import com.oncha.oncha_web.feature.product.productCart.model.ProductCartSaveRequest;
import com.oncha.oncha_web.util.EntityUtil;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductCartService {

    private final ProductBoardRepository productBoardRepository;

    private final ProductCartRepository productCartRepository;

    public List<ProductCartDTO> getMyCart(Long memberId, Pageable pageable) {
        return productCartRepository.findAllByCreatedBy(memberId, pageable).getContent()
            .stream().map(ProductCartDTO::of).collect(Collectors.toList());
    }

    @Transactional
    public Long saveProduct(ProductCartSaveRequest request) {
        ProductBoard productBoard = getProductBoardById(request.getProductBoardId());
        ProductCart productCart = productCartRepository.save(new ProductCart(productBoard, request.getAmount()));
        return productCart.getId();
    }

    @Transactional
    public void deleteCart(Long cartId, Long memberId) {
        ProductCart productCart = getProductCartById(cartId);
        validationDelete(productCart, memberId);

        productCartRepository.delete(productCart);
    }

    private void validationDelete(ProductCart productCart, Long memberId) {
        if (!EntityUtil.isOwner(productCart, memberId)) {
            throw new AccessDinedException(productCart.getId(), memberId, ProductCart.class);
        }
    }

    private ProductBoard getProductBoardById (Long productBoardId) {
        return productBoardRepository.findById(productBoardId).orElseThrow(
            () -> new EntityNotFoundException(productBoardId, ProductBoard.class)
        );
    }

    private ProductCart getProductCartById (Long productCartId) {
        return productCartRepository.findById(productCartId).orElseThrow(
            () -> new EntityNotFoundException(productCartId, ProductCart.class)
        );
    }
}
