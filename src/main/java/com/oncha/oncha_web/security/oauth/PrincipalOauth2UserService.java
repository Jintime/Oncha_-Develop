package com.oncha.oncha_web.security.oauth;


import com.oncha.oncha_web.domain.user.model.Member;
import com.oncha.oncha_web.domain.user.model.MemberRepository;
import com.oncha.oncha_web.security.auth.PrincipalDetails;
import com.oncha.oncha_web.security.provider.UserInfoProvider;
import com.oncha.oncha_web.security.provider.userinfo.OAuth2UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User.getAttributes());
        OAuth2UserInfo oAuth2UserInfo = UserInfoProvider.getOAuthUserInfo(userRequest, oAuth2User);

        String providerId = oAuth2UserInfo.getProviderId();
        String provider = oAuth2UserInfo.getProvider();
        String userId = provider+"_"+providerId;
        String email = oAuth2UserInfo.getEmail();
        String username = oAuth2UserInfo.getName();
        String role = "ROLE_USER";

        Optional<Member> oMember = memberRepository.findByUserId(userId);
        Member member;
        if (oMember.isPresent()) {
            member = oMember.get();
        } else {
            member = new Member(userId, username, email, role, provider, providerId);
            member = memberRepository.save(member);
        }
        return new PrincipalDetails(member.getId(), member.getRole(), oAuth2User.getAttributes());
    }
}
