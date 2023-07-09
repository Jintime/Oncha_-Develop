package com.oncha.oncha_web.domain.store.orderInfo.repository;

import com.oncha.oncha_web.domain.store.orderInfo.model.UsersOrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersOrderInfoRepository extends JpaRepository<UsersOrderInfo,Long> {
}
