package br.com.pikomon.Pikomon.infra.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;



@RestControllerAdvice
public class Exceptions {

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity userNotFoundException(Integer id){
        return ResponseEntity.notFound().build();
    }

}
