package br.com.pikomon.Pikomon.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ObjectNotFoundException extends RuntimeException{

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<String> ObjectNotFoundException( ObjectNotFoundException e){
        return new ResponseEntity<>("Object not found", HttpStatus.NOT_FOUND);
    }
}
