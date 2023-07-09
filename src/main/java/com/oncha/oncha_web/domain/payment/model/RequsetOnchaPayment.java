package com.oncha.oncha_web.domain.payment.model;

public interface RequsetOnchaPayment {
    public Long getId();
    public String getImpUid();
    public String getPayment_price();
    public String getProduct_name();
    public String getPayment_status();
    public String getBuyer_name();
    public String getBuyer_email();
    public String getPost_code();
    public String getAddress();
    public String getPhone_number();
    public Long getSeller_id();


}
