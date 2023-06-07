package com.oncha.oncha_web.feature.user.model;

import com.oncha.oncha_web.domain.user.model.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private Long id;

    private String userId;
    private String name;
    private String phoneNumber;
    private String email;
    private LocalDateTime birth;
    private String gender;
    private String grade;
    private boolean disable;
    private String provider;
    private String providerId;
    private List<AddressDTO> addressDTOList;



}
