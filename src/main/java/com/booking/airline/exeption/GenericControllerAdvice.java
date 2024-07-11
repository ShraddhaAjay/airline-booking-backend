package com.booking.airline.exeption;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenericControllerAdvice {

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<String> handleLoginExceptions(LoginException loginException){
        return new ResponseEntity<>(loginException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
