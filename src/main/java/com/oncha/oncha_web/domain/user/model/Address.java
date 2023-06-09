package com.oncha.oncha_web.domain.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@AllArgsConstructor
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

}
