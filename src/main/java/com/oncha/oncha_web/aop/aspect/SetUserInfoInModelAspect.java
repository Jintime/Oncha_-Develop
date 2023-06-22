package com.oncha.oncha_web.aop.aspect;

import com.oncha.oncha_web.feature.user.model.MemberDTO;
import com.oncha.oncha_web.feature.user.service.MemberService;
import com.oncha.oncha_web.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Aspect
@Component
@RequiredArgsConstructor
public class SetUserInfoInModelAspect {

    private final MemberService memberService;

    @Around("@annotation(com.oncha.oncha_web.aop.annotation.SetUserInfoInModel)")
    public Object setUserIdInModel(ProceedingJoinPoint joinPoint) throws Throwable {
        Long id = SecurityUtil.getCurrentId().orElse(null);

        if (id == null) {
            return joinPoint.proceed();//이 코드 전후가 메소드 시작전과 후 // proceed()의 리턴값은 가로챈 메소드의 리턴 결과
        }

        MemberDTO member = memberService.findById(id);

        // Get the Model object from the controller method
        Object[] args = joinPoint.getArgs();
        Model model = findModelObject(args);

        // Set the property in the Model object
        if (model != null) {
            model.addAttribute("userInfo", member);
        }

        // Proceed with the execution of the controller method
        return joinPoint.proceed();
    }

    // Helper method to find the Model object in the controller method arguments
    private Model findModelObject(Object[] args) {
        for (Object arg : args) {
            if (arg instanceof Model) {
                return (Model) arg;
            }
        }
        return null;
    }
}
