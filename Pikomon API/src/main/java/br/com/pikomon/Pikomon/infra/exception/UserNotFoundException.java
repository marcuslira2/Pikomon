package br.com.pikomon.Pikomon.infra.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserNotFoundException extends Exception{

    private static final Logger log = LoggerFactory.getLogger(UserNotFoundException.class);
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> notFoundException(){

        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);

    }
}
