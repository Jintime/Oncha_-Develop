package com.oncha.oncha_web.domain.productReview.model;

public interface RequestProductReview {
    public Long getId();
    public String getContent();
    public int getLike();
    public int getProduct_star();
}
