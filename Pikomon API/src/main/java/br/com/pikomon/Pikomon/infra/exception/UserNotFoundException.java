package br.com.pikomon.Pikomon.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserNotFoundException extends Exception{
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> notFoundException(){
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }
}
