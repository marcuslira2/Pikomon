package br.com.pikomon.pikomon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder messages = new StringBuilder();
        BindingResult bindingResult = e.getBindingResult();

        bindingResult.getFieldErrors().forEach(error -> {
            messages.append(error.getDefaultMessage()).append(" \n");
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messages.toString());
    }
}
