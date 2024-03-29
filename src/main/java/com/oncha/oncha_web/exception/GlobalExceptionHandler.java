package com.oncha.oncha_web.exception;

import com.oncha.oncha_web.exception._40x.BadRequestException;
import com.oncha.oncha_web.exception._40x.UnauthorizedException;
import com.oncha.oncha_web.mail.MailingService;
import com.oncha.oncha_web.util.ExceptionUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@ControllerAdvice(annotations = Controller.class)
@RequiredArgsConstructor
@Order(2)
public class GlobalExceptionHandler {

    private final MailingService mailingService;
    private String errorSubject = "온차 서버 web 에러 메세지입니다.";

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleEntityNotFound(EntityNotFoundException ex) {
//        mailingService.sendErrorMessage(errorSubject, ExceptionUtil.getStackTrace(ex));
        ex.printStackTrace();
        return "error/400";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleNotValidException(HttpServletRequest request, HttpServletResponse response, Model model, MethodArgumentNotValidException ex) {
//        mailingService.sendErrorMessage(errorSubject, ExceptionUtil.getStackTrace(ex));
        try {
            ex.printStackTrace();
            String url = request.getRequestURL().toString();
            model.addAttribute("message", ex.getMessage());
            response.sendRedirect(url);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBadRequest(BadRequestException ex) {
//        mailingService.sendErrorMessage(errorSubject, ExceptionUtil.getStackTrace(ex));
        ex.printStackTrace();
        return "error/400";
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleUnAuthorized(UnauthorizedException ex) {
//        mailingService.sendErrorMessage(errorSubject, ExceptionUtil.getStackTrace(ex));
        ex.printStackTrace();
        return "error/401";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception ex) {
//        mailingService.sendErrorMessage(errorSubject, ExceptionUtil.getStackTrace(ex));
        ex.printStackTrace();
        return "error/500";
    }

}
