package com.example.bankingapi.exception;

import com.example.bankingapi.base.BaseError;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApiException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseError<?> handlerError(MethodArgumentNotValidException e){
        List<Map<String, String>> errors = new ArrayList<>();
        for(FieldError fieldError: e.getFieldErrors()){
            Map<String, String> errorhandler = new HashMap<>();
            errorhandler.put("name",fieldError.getField());
            errorhandler.put("error",fieldError.getDefaultMessage());
            errors.add(errorhandler);
        }
        return BaseError.builder()
                .timestamp(LocalDateTime.now())
                .error(errors)
                .message("Stupid developer! You're get Error!!")
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ResponseStatusException.class)
    public BaseError<?> handleServiceExcetion(ResponseStatusException e){
        return BaseError.builder()
                .status(false)
                .code(e.getStatusCode().value())
                .timestamp(LocalDateTime.now())
                .message("Something went wrong..!")
                .error(e.getReason())
                .build();
    }
}
