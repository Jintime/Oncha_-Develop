package com.oncha.oncha_web.domain.company.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "company")
public class Company {
    @Id
    private Long id;

    @Column(length = 200)
    private String company_name;

    @Column(length = 200)
    private String company_info;

    @Column(length = 255)
    private String company_url;

    private String company_zipcode;
    private String company_address;
    private String company_address_detail;

}
