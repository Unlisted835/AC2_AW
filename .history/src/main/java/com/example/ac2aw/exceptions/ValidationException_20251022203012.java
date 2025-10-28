package com.example.ac2aw.exceptions;

import java.util.List;

public class ValidationException extends RuntimeException {

   private List<Failure> failures;

   public ValidationException(String message) {
      super(message);
   }


   public record Failure(String field, String message) { }
}
