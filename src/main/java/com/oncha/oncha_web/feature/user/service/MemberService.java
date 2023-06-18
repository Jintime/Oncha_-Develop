package com.oncha.oncha_web.feature.user.service;

import com.oncha.oncha_web.domain.user.model.Member;
import com.oncha.oncha_web.domain.user.repository.MemberRepository;
import com.oncha.oncha_web.exception._40x.ExistNickNameException;
import com.oncha.oncha_web.feature.user.model.MemberDTO;
import com.oncha.oncha_web.feature.user.model.RegisterRequest;
import com.oncha.oncha_web.feature.user.repository.MemberQueryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;

    @Transactional
    public boolean register(Long id, RegisterRequest request) {
        Member member = memberRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("데이터베이스에 현재 해당 하는 사용자 정보가 없습니다."));

        if (memberRepository.existsByNickName(request.getNickName())) {
            throw new ExistNickNameException(request.getNickName());
        }

        member.register(request);
        return true;
    }

    public MemberDTO findById(Long id){return memberQueryRepository.findById(id);}

}
