package com.oncha.oncha_web.domain.productBoard.model;

public interface RequestProductBoard {

    Long getCompanyId();
    String getTitle();
    String getDetail();
    String getProductName();
    Integer getWeight();
    Integer getPrice();
    Integer getProductCount();
    TeaCategory getTeaCategory();
}
