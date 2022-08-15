package com.example.demo.handler;

import com.example.demo.exception.ElectricityServiceValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityExistsException;
import java.util.Date;

@RestControllerAdvice
@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(ElectricityServiceValidationException.class)
    public ResponseEntity handleElectricityServiceValdationException(ElectricityServiceValidationException ex, WebRequest request) {
        ErrorDetails error = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        ErrorDetails error = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        ErrorDetails error = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<Object> handleEntityExistsException(EntityExistsException ex, WebRequest request) {
        ErrorDetails error = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
