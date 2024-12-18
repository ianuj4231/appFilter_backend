package com.fashion.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidPriceException.class)
    public ResponseEntity<String> handleInvalidPrice(InvalidPriceException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(ServiceLayerException.class)
    public ResponseEntity<String> handleServiceLayerException(ServiceLayerException ex) {
        System.err.println("Service Layer Exception: " + ex.getMessage());
        return new ResponseEntity<>("an internal server error occurred, please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
}}
