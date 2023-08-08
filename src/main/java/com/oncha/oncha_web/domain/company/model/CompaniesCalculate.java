package com.oncha.oncha_web.domain.company.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="companies_calculate")
public class CompaniesCalculate {
    @Id
    private Long id;

    private Long totalAmount;

    private Long salesAmount;

    private Long calculateAmount;


}
