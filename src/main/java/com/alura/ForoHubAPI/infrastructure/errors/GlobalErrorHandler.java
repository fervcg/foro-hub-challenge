package com.alura.ForoHubAPI.infrastructure.errors;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alura.ForoHubAPI.infrastructure.errors.exception.BusinessRulesValidationsException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalErrorHandler {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> noFoundErrorHandler(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorValidationData>> noValidDataHandler(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors().stream().map(ErrorValidationData::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(BusinessRulesValidationsException.class)
    public ResponseEntity<String> validationErrorHandler(BusinessRulesValidationsException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> dataIntegrityErrorHandler(DataIntegrityViolationException e){
        return ResponseEntity.badRequest().body("Ya existe un registro con la informacion suministrada");
    }


    private record ErrorValidationData(String field, String error) {

        public ErrorValidationData(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
