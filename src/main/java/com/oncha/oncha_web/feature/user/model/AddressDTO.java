package com.oncha.oncha_web.feature.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private Long id;

    private String default_address;
    private String spare_address;
    private String spare_address2;
}
