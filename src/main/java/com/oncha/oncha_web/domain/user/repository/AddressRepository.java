package com.oncha.oncha_web.domain.user.repository;

import com.oncha.oncha_web.domain.user.model.Address;
import com.oncha.oncha_web.feature.user.model.AddressDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Long> {
    Address findByMemberId(Long memberId);
}
