package com.misael.financialservice.handlers;

import com.misael.financialservice.exceptions.UnauthorizedTransactionException;
import com.misael.financialservice.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler(UnauthorizedTransactionException.class)
    public ResponseEntity<String> unauthorizedTransaction(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Transação não autorizada");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFound(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encontrado");
    }
}
