package com.oncha.oncha_web.domain.productBoard.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class TeaCategory {

    @Column(length = 50)
    private String origin_nation; // 2

    @Column(length = 15)
    private String type;

    @Column(length = 30)
    private String flavor; // 1번 향미

    private boolean blended; // 4 블랜딩

    private boolean caffeine; // 3 카페인 유무

    @Column(length = 30)
    private String category; //5 녹차 인지 홍차인지

}
