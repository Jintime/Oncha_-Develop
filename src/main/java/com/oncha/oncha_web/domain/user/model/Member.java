package com.oncha.oncha_web.domain.user.model;

import com.oncha.oncha_web.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "members")
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseTimeEntity {
    @Id//pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, unique = true)
    private String userId;
    @Column(length = 30)
    private String name;
    @Column(length = 13)
    private String phoneNumber;
    private String email;
    private LocalDateTime birth;
    @Column(length = 10)
    private String gender;
    //등급
    @Column(length = 10)
    private String grade;
    @Column(length = 10)
    private String role;
    @Column(nullable = false)
    private boolean disable;
    @Column(nullable = false)
    private boolean allow;
    private String provider;
    private String providerId;

    public Member(String userId, String name ,String email ,String role, String provider, String providerId) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.role = role;
        this.grade = "normal";
        this.allow = false;
        this.disable = false;
        this.provider = provider;
        this.providerId = providerId;
    }
}
