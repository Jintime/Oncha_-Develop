package com.oncha.oncha_web.domain.productBoard.model;

public interface RequestProductBoard {

    public Long getId();
    public String getTitle();
    public String getDetail();
    public String getProductName();
    public String getOriginNation();
    public String getType();
    public String getFlavor();
    public String getCategory();
    public int getWeight();
    public int getPrice();
    public int getProductCount();
    public int getView();
    public int getLike();
    public boolean isBlended();
    public boolean isCaffeine();
    public boolean isAllow();

}
