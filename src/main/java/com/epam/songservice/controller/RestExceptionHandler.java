package com.epam.songservice.controller;

import com.epam.songservice.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<String> notValidFileOrFileLimitExceedException(MethodArgumentNotValidException ex) {
        ex.printStackTrace();
        log.error(ex.getMessage());
        return ResponseEntity
                .badRequest()
                .body("Song metadata missing validation error");
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<String> entityNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> internalServerException(Exception ex) {
        ex.printStackTrace();
        log.error(ex.getMessage());
        return ResponseEntity
                .internalServerError()
                .body("An internal server error has occurred");
    }
}
