package br.com.pikomon.Pikomon.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BadRequestCreation extends Exception{
    @ExceptionHandler(BadRequestCreation.class)
    public ResponseEntity<String> badRequest(){
        return new ResponseEntity<>("Error when passing object attributes", HttpStatus.BAD_REQUEST);
    }
}
