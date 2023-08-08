package com.oncha.oncha_web.domain;

import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;

@MappedSuperclass
@Getter
public class BaseDeleteEntity {

    private LocalDateTime deletedAt;


}
