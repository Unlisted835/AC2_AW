package com.example.ac2aw.exceptions;

public class ValidationException extends RuntimeException {

   private List<ValidationFailure> failures;

   public ValidationException(String message) {
      super(message);
   }


}
