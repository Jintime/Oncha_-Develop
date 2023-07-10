package com.oncha.oncha_web.domain.store.orderInfo.repository;

import com.oncha.oncha_web.domain.store.orderInfo.model.NonUsersOrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NonUsersOrderInfoRepository extends JpaRepository<NonUsersOrderInfo,Long> {
}
