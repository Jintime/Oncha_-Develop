package com.oncha.oncha_web.util;

import com.oncha.oncha_web.domain.BaseDeleteEntity;
import com.oncha.oncha_web.domain.BaseEntity;

public class EntityUtil {

    public static boolean isOwner(BaseEntity baseEntity, Long memberId) {
        return baseEntity.getCreatedBy().equals(memberId);
    }

}
