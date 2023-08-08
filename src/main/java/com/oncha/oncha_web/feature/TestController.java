package com.oncha.oncha_web.feature;

import com.oncha.oncha_web.aop.annotation.SetUserInfoInModel;
import com.oncha.oncha_web.domain.user.model.Member;
import com.oncha.oncha_web.exception._40x.EntityNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing")
public class TestController {

    @GetMapping
    public TestDTO test1() {
        throw new EntityNotFoundException(1L, Member.class);
    }
}
