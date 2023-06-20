package com.oncha.oncha_web.feature.aspect;

import com.oncha.oncha_web.feature.user.model.MemberDTO;
import com.oncha.oncha_web.feature.user.service.MemberService;
import com.oncha.oncha_web.util.SecurityUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Optional;

@Aspect
@Component
public class UserAspect {

    @Autowired
    private MemberService memberService;

    @Around("execution(* com.oncha.oncha_web.feature.myoncha.controller.normal.web.*.*(..))||" +
            "execution(* com.oncha.oncha_web.feature.product.productBoard.controller.normal.*.*(..)) ||" +
            "execution(* com.oncha.oncha_web.feature.store.controller.normal.*.*(..))" +
            "&& @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public Object setCurrentUser(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Model model = findModelArgument(args);

        if (model != null) {
            Optional<Long> userId = SecurityUtil.getCurrentId();
            if (userId.isPresent()) {
                MemberDTO memberDTO = memberService.findById(userId.get());
                model.addAttribute("user", memberDTO);
            }
        }

        return joinPoint.proceed();
    }

    private Model findModelArgument(Object[] args) {
        for (Object arg : args) {
            if (arg instanceof Model) {
                return (Model) arg;
            }
        }
        return null;
    }
}