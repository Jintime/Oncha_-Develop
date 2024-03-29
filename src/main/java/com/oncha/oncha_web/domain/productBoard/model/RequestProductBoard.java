package com.oncha.oncha_web.domain.productBoard.model;

public interface RequestProductBoard {

    public Long getId();

    public Long getCompanyId();
    public String getTitle();
    public String getDetail();
    public String getProduct_name();
    public String getOrigin_nation();
    public String getType();
    public String getFlavor();
    public String getCategory();
    public String getBlended();
    public int getWeight();
    public int getPrice();
    public int getProduct_count();
    public int getView();
    public int getLike();

    public boolean isCaffeine();

}
