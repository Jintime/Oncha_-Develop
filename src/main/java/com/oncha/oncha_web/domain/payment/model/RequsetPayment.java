package com.oncha.oncha_web.domain.payment.model;

public interface RequsetPayment {
    public Long getId();
    public String getImpUid();
    public String getPayment_price();
    public String getPayment_status();
    public String getBuyer_name();
    public String getBuyer_email();

}
