package com.oncha.oncha_web.domain.productBoard.model;

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
@Table(name = "product_board")
public class ProductBoard {

    @Id
    private Long id;

    @Column(length = 200)
    private String title;

    @Column(length = 500)
    private String detail;

    @Column(length = 200)
    private String product_name;

    @Column(length = 50)
    private String origin_nation;

    @Column(length = 15)
    private String type;

    @Column(length = 30)
    private  String flavor;

    @Column(length = 30)
    private String category;


    private int weight;
    private int price;
    private int product_count;
    private int view;
    private int like;

    private boolean blended;
    private boolean caffeine;



}
