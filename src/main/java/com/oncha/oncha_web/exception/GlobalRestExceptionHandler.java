package com.oncha.oncha_web.exception;

import com.oncha.oncha_web.exception._40x.BadRequestException;
import com.oncha.oncha_web.exception._40x.UnauthorizedException;
import com.oncha.oncha_web.feature.ResponseType;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RestControllerAdvice(annotations = RestController.class)
public class GlobalRestExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseType<String> handleEntityNotFound(EntityNotFoundException ex) {
        return new ResponseType<>(ex.getMessage());
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
