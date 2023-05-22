package com.oncha.oncha_web.feature.user.service;

import com.oncha.oncha_web.domain.user.model.Member;
import com.oncha.oncha_web.domain.user.repository.MemberRepository;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    
}
