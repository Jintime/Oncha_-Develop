package com.oncha.oncha_web.domain.company.model;

import com.oncha.oncha_web.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="billing_request")
public class CompaniesBillingRequest extends BaseEntity {
    @Id
    private Long id;

    private Long billingAmount;

    private String state;

}
