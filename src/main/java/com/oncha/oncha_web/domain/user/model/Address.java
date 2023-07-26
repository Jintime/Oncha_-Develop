package com.oncha.oncha_web.domain.user.model;

import com.oncha.oncha_web.feature.user.model.AddressDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Address(Long id, String default_zipcode,String default_address,String default_address_detail,
                   String spare_zipcode,String spare_address, String spare_address_detail,
                   String spare2_zipcode,String spare2_address,String spare2_address_detail){
        this.id = id;
        this.default_zipcode = default_zipcode;
        this.default_address = default_address;
        this.default_address_detail = default_address_detail;
        this.spare_zipcode = spare_zipcode;
        this.spare_address = spare_address;
        this.spare_address_detail = spare_address_detail;
        this.spare2_zipcode = spare2_zipcode;
        this.spare2_address = spare2_address;
        this.spare2_address_detail = spare2_address_detail;
    }

    public static Address toAddress(AddressDTO addressDTO){
        return Address.builder()
                .id(addressDTO.getId())
                .default_zipcode(addressDTO.getDefault_zipcode())
                .default_address(addressDTO.getDefault_address())
                .default_address_detail(addressDTO.getDefault_address_detail())
                .spare_zipcode(addressDTO.getSpare_zipcode())
                .spare_address(addressDTO.getSpare_address())
                .spare_address_detail(addressDTO.getSpare_address_detail())
                .spare2_zipcode(addressDTO.getSpare2_zipcode())
                .spare2_address(addressDTO.getSpare2_address())
                .spare2_address_detail(addressDTO.getSpare2_address_detail())
                .build();

    }

}
