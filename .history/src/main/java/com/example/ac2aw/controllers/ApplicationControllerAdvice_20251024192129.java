package com.example.ac2aw.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.ac2aw.exceptions.ValidationException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

   @ExceptionHandler(ValidationException.class)
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   public List<ValidationException.Failure> handlerValidationException(ValidationException ex) {
      return ex.getFailures();

   }

   @ExceptionHandler(EntityNotFoundException.class)
   @ResponseStatus(HttpStatus.NOT_FOUND)
   public String handleEntityNotFoundException(EntityNotFoundException ex) {
      return ex.getMessage();
   }
   
}
