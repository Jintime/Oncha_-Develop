package com.oncha.oncha_web.feature.user.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.oncha.oncha_web.domain.productBoard.model.ProductBoard;
import com.oncha.oncha_web.domain.user.model.Member;
import com.oncha.oncha_web.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public void allow(Long id) throws IOException {
            Member member = memberRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("해당 정보가 없습니다"));
            ///추후 수정이 필요할거같음
            member.allowed();
    }


}
