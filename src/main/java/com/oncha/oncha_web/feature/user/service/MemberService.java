package com.oncha.oncha_web.feature.user.service;

import com.oncha.oncha_web.domain.user.model.Member;
import com.oncha.oncha_web.domain.user.repository.MemberRepository;
import com.oncha.oncha_web.feature.user.model.RegisterRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public boolean signAdd(Long id, RegisterRequest request) {
        Member member = memberRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("데이터베이스에 현재 해당 하는 사용자 정보가 없습니다."));
        member.signup(request);
        return true;
    }


}
