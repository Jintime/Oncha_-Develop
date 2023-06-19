package com.oncha.oncha_web.feature.user.model;

import com.oncha.oncha_web.domain.user.model.Member;
import lombok.*;

import java.time.LocalDate;
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
    private LocalDate birth;
    private String gender;
    private String grade;
    private List<AddressDTO> addressDTOList;
}
