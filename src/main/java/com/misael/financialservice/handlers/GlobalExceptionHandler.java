package com.misael.financialservice.handlers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail methodArgumenProblemDetail(MethodArgumentNotValidException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getBindingResult().getFieldErrors()
        .stream().map(FieldError::getDefaultMessage).toList().toString());
    }
}
