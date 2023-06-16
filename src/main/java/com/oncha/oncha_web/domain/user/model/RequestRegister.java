package com.oncha.oncha_web.domain.user.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface RequestRegister {

    String getName();

    String getPhoneNumber();

    String getNickName();

    LocalDate getBirth();

    String getGender();
}
