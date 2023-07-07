package com.oncha.oncha_web.feature.user.service;

import com.oncha.oncha_web.domain.user.model.Member;
import com.oncha.oncha_web.domain.user.repository.MemberRepository;
import com.oncha.oncha_web.exception._40x.ExistNickNameException;
import com.oncha.oncha_web.feature.user.model.MemberDTO;
import com.oncha.oncha_web.feature.user.model.RegisterRequest;
import com.oncha.oncha_web.feature.user.repository.MemberQueryRepository;
import com.oncha.oncha_web.security.jwt.redis.exception.CustomJwtException;
import com.oncha.oncha_web.security.jwt.redis.exception.NotEqualRefreshTokenException;
import com.oncha.oncha_web.security.jwt.redis.repository.RefreshTokenInfo;
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

        if (memberRepository.existsByNickName(request.getNickname())) {
            throw new ExistNickNameException(request.getNickname());
        }

        member.register(request);
        return member.isAllow();
    }

    @Transactional
    public void setNewRefresh (Long id, String refresh) {
        Member member = memberRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("데이터베이스에 현재 해당 하는 사용자 정보가 없습니다."));

        member.setRefreshToken(refresh);
    }

    /**
     * Member에 있는 refreshtoken과 대조후 새로운 refreshtoken을 저장
     */
    @Transactional
    public void processingResetRefreshToken(Long userId, String olderRefresh, String reGenRefresh) throws CustomJwtException {
        Member member =  memberRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("데이터베이스에 현재 해당 하는 사용자 정보가 없습니다."));

        if (!member.getRefreshToken().equals(olderRefresh)) {
            throw new NotEqualRefreshTokenException(member.getRefreshToken(), olderRefresh);
        }

        member.setRefreshToken(reGenRefresh);
    }

    private Member findEntityById (Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("데이터베이스에 현재 해당 하는 사용자 정보가 없습니다."));

    }

    public MemberDTO findById(Long id){return memberQueryRepository.findById(id);}

}
