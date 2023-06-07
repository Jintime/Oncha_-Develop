package com.oncha.oncha_web.feature.user.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private String name;
    private String phoneNumber;
    private String email;
    private LocalDateTime birth;
    private String gender;
    private String grade;
    private AddressInfo addressInfo;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class AddressInfo {

        private String zonecode;

        private String address;

        private String roadAddress;

        private String jibunAddress;

        private String buildingName;

        private String sido;

        private String sigungu;

        private String roadname;

    }

}
