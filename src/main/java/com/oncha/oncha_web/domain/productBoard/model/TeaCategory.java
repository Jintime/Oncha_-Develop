package com.oncha.oncha_web.domain.productBoard.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TeaCategory {


    @Column(length = 15)
    private String type;

    @Column(length = 30)
    private String flavor; // 1번 향미

    @Column(length = 50)
    private String originNation; // 2

    private boolean caffeine; // 3 카페인 유무

    private boolean blended; // 4 블랜딩

    @Column(length = 30)
    private String category; //5 녹차 인지 홍차인지

}
