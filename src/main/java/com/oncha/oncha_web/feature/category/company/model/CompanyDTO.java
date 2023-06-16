package com.oncha.oncha_web.feature.category.company.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompanyDTO {

    private Long id;
    private String company_name;
    private String company_info;
    private String company_url;

    private String company_zipcode;
    private String company_address;
    private String company_address_detail;
}
