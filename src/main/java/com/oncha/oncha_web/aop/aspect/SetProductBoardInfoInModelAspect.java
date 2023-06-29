package com.oncha.oncha_web.aop.aspect;

import com.oncha.oncha_web.feature.product.productBoard.model.ProductBoardDTO;
import com.oncha.oncha_web.feature.product.productBoard.service.ProductBoardService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Aspect
@Component
@RequiredArgsConstructor
public class SetProductBoardInfoInModelAspect {

    private final ProductBoardService productBoardService;

    @Around("@annotation(com.oncha.oncha_web.aop.annotation.CompleteInfoModel) " +
            "|| @annotation(com.oncha.oncha_web.aop.annotation.SetProductInfoModel)")
    public Object setProductBoardInfoInModel(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Long id = findIdArgument(args);


        Object result = joinPoint.proceed();

        if (id != null && result instanceof Model) {
            ProductBoardDTO productBoardDTO = productBoardService.findById(id);

            Model model = (Model) result;
            model.addAttribute("productBoard", productBoardDTO);
        }

        return result;
    }

    private Long findIdArgument(Object[] args) {
        for (Object arg : args) {
            if (arg instanceof Long) {
                return (Long) arg;
            }
        }
        return null;
    }
}