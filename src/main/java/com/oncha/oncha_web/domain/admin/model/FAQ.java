package com.oncha.oncha_web.domain.admin.model;

import com.oncha.oncha_web.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "faq")
public class FAQ extends BaseEntity {
    @Id
    private Long id;

    private String title;
    private String detail;
}
