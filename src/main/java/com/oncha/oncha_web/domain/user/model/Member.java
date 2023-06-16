package com.oncha.oncha_web.domain.user.model;

import com.oncha.oncha_web.domain.BaseTimeEntity;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private LocalDate birth;
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

    @OneToMany(mappedBy = "member",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Address> addressList=new ArrayList<>();

    public void allowed() {
        this.allow = true;
    }
    public Member(String userId, String email ,String role, String provider, String providerId) {
        this.userId = userId;
        this.email = email;
        this.role = role;
        this.grade = "normal";
        this.allow = false;
        this.disable = false;
        this.provider = provider;
        this.providerId = providerId;
    }

    public void signup(RequestRegister request) {
        this.name = request.getName();
        this.birth = request.getBirth();
        this.gender = request.getGender();
        this.phoneNumber = request.getPhoneNumber();
    }
}
