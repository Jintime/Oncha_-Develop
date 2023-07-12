package com.oncha.oncha_web.domain.productBoard.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.hibernate.annotations.Comment;

@Embeddable
@Getter
public class TeaCategory {

    private Integer savory;

    private Integer acidity;

    private Integer bitter;

    private String flavor;

    private Boolean caffeine;

    @Comment("차종 ex) 녹차, 홍차..")
    private String teaType;

    @Comment("싱글오리진, 블렌디드")
    private String blending;

    //추천알고리즘 제외
    private String originNation;

}
