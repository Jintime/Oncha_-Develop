package com.oncha.oncha_web.domain.user.repository;

import com.oncha.oncha_web.domain.user.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUserId(String userId);

    boolean existsByUserId(String userId);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
