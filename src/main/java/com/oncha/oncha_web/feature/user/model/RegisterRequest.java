package com.oncha.oncha_web.feature.user.model;

import com.oncha.oncha_web.domain.user.model.RequestRegister;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.*;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest implements RequestRegister {

    @Min(value = 2, message = "이름의 최소 길이는 2자 입니다.")
    private String name;

    @NotNull(message = "휴대폰 번호를 입력해 주세요")
    private String phoneNumber;

    @NotNull(message = "닉네임을 입력해 주세요")
    private String nickName;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDate birth;

    @NotNull(message = "성별을 입력해 주세요")
    private String gender;
}
