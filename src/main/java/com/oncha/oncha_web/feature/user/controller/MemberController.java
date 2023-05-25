package com.oncha.oncha_web.feature.user.controller;


import com.oncha.oncha_web.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

}
