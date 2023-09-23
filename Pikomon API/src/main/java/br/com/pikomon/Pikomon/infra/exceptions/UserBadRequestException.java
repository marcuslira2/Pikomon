package br.com.pikomon.Pikomon.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserBadRequestException extends RuntimeException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> userNotFoundException(MethodArgumentNotValidException e){

        return new ResponseEntity<>("''"+e.getFieldError().getField()+"'' "+ e.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }
}
