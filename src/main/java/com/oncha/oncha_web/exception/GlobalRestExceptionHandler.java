package com.oncha.oncha_web.exception;

import com.oncha.oncha_web.exception._40x.BadRequestException;
import com.oncha.oncha_web.exception._40x.UnauthorizedException;
import com.oncha.oncha_web.feature.ResponseType;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RestControllerAdvice(annotations = RestController.class)
@Order(1)
public class GlobalRestExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseType<String> handleEntityNotFound(EntityNotFoundException ex) {
        return new ResponseType<>(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseType<ArrayList<String>> handleValidException(MethodArgumentNotValidException ex) {
        ArrayList<String> messageList = new ArrayList<>();
        try {
            for (ObjectError message : ex.getBindingResult().getAllErrors()) {
                messageList.add(message.getDefaultMessage());
            }
        }catch (Exception e) {
           messageList.add("요청이 옳지 않습니다.");
        }

        return new ResponseType<>(messageList);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseType<String> handleBadRequest(BadRequestException ex) {
        return new ResponseType<>(ex.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseType<String> handleUnAuthorized(UnauthorizedException ex) {
        return new ResponseType<>(ex.getMessage());
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseType<String> handleException(Exception ex) {
        return new ResponseType<>(ex.getMessage());
    }
}
