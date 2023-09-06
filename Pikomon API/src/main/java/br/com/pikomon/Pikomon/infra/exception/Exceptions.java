package br.com.pikomon.Pikomon.infra.exception;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class Exceptions {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity notFound404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(NoSuchMethodException.class)
    public ResponseEntity elementNull(){
        return ResponseEntity.notFound().build();
    }
}
