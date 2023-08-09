package com.oncha.oncha_web.domain.user.model;

import com.oncha.oncha_web.domain.BaseTimeEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column
    private String nickName;

    //등급
    @Column(length = 10)
    private String grade;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private boolean disable;

    @Column(nullable = false)
    private boolean allow;

    private String provider;
    private String providerId;

    private String refreshToken;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Address> addressList = new ArrayList<>();

    public void allowed() {
        this.allow = true;
    }

    public void setRefreshToken (String token) {
        refreshToken = token;
    }

    public Member(String userId, String email, Role role, String provider, String providerId) {
        this.userId = userId;
        this.email = email;
        this.role = role;
        this.grade = "normal";
        this.allow = false;
        this.disable = false;
        this.provider = provider;
        this.providerId = providerId;
    }

    public void register(RequestRegister request) {
        this.name = request.getName();
        this.birth = request.getBirth();
        this.gender = request.getGender();
        this.phoneNumber = request.getPhoneNumber();
        this.nickName = request.getNickname();
        this.allow = true;
    }
}
