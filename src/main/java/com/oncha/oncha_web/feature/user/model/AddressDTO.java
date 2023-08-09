package com.oncha.oncha_web.feature.user.model;

import com.oncha.oncha_web.domain.productBoard.model.ProductFile;
import com.oncha.oncha_web.domain.user.model.Address;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDTO {
    private Long id;

    private String default_zipcode;
    private String default_address;
    private String default_address_detail;

    private String spare_zipcode;
    private String spare_address;
    private String spare_address_detail;

    private String spare2_zipcode;
    private String spare2_address;
    private String spare2_address_detail;


}
