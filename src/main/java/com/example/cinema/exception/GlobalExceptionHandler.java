package com.example.cinema.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CinemaException.class)
    public ResponseEntity<?> handleCinemaException(CinemaException ex) {
        return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
    }
}