package com.example.ac2aw.exceptions;

public class ValidationException extends RuntimeException {

   private List<Failure> failures;

   public ValidationException(String message) {
      super(message);
   }


}
