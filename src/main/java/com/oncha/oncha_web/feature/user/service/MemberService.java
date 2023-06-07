package com.oncha.oncha_web.feature.user.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.oncha.oncha_web.domain.user.model.Member;
import com.oncha.oncha_web.domain.user.repository.MemberRepository;
import com.oncha.oncha_web.feature.user.model.MemberDTO;
import com.oncha.oncha_web.feature.user.repository.MemberQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;
    @Transactional
    public void allow(Long id) throws IOException {
            Member member = memberRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("해당 정보가 없습니다"));
            ///추후 수정이 필요할거같음
            member.allowed();
    }

    public MemberDTO findById(Long id){return memberQueryRepository.findById(id);}




}
