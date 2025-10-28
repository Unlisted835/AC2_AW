package com.example.ac2aw.exceptions;

import java.util.List;

public class ValidationException extends RuntimeException {

   private List<Failure> failures;

   public ValidationException(String message) {
      super(message);
   }
   public ValidationException(String message, List<Failure> failures) {
      super(message);
      this.failures = failures;
   }
   public ValidationException(List<Failure> failures) {
      this.failures = failures;
   }
   public ValidationException(String field, String message) {
      this.failures = List.of(new Failure(field, message));
   }
   public List<Failure> getFailures() {
      return failures;
   }


   public record Failure(String field, String message) { }
}
