package com.oncha.oncha_web.feature.user.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.oncha.oncha_web.domain.user.model.RequestRegister;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest implements RequestRegister {

    @Size(min = 2, message = "이름의 최소 길이는 2자 입니다.")
    private String name;

//    @NotNull(message = "휴대폰 번호를 입력해 주세요")
    private String phoneNumber;

    @Size(min = 3, message = "닉네임의 최소길이는 3자 입니다.")
    private String nickname;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private LocalDate birth;

    @NotNull(message = "주민번호 뒷자리가 올바르지 않습니다.")
    private String gender;
}
