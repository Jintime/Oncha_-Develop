package com.oncha.oncha_web.feature.product.productCart.controller;

import com.oncha.oncha_web.feature.product.productCart.model.ProductCartSaveRequest;
import com.oncha.oncha_web.feature.product.productCart.service.ProductCartService;
import com.oncha.oncha_web.security.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/cart")
public class ProductCartController {

    private final ProductCartService productCartService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCart(@RequestBody ProductCartSaveRequest request) {
        productCartService.saveProduct(request);
    }

    @DeleteMapping("/{cartId}")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteCart(@PathVariable Long cartId,
        @AuthenticationPrincipal PrincipalDetails principalDetails) {
        productCartService.deleteCart(cartId, principalDetails.getId());
    }
}
