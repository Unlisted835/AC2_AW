package com.example.ac2aw.controllers;

import java.util.*;
import java.util.stream.*;

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
   public List<ValidationException.Failure> handleValidationException(ValidationException ex) {
      return ex.getFailures();
   }

   @ExceptionHandler(EntityNotFoundException.class)
   @ResponseStatus(HttpStatus.NOT_FOUND)
   public String handleEntityNotFoundException(EntityNotFoundException ex) {
      return ex.getMessage();
   }

   @ExceptionHandler(Exception.class)
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   public ExceptionDTO handleUntreatedException(Exception e) {
      return new ExceptionDTO(e);
   }

   public record ExceptionDTO (String exceptionClass, String message, String stackTrace) {
      public ExceptionDTO(Exception e) {
         this(e.getClass().getSimpleName(), e.getMessage(), Arrays.stream(e.getStackTrace())
               .map(StackTraceElement::toString)
               .collect(Collectors.joining("     |     ")));
      }
   }
}
