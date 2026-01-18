package com.ganatan.starter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
    return new ErrorResponse("BAD_REQUEST", "INVALID_PATH_PARAMETER: " + ex.getName());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleValidation(MethodArgumentNotValidException ex) {
    String message = ex.getBindingResult()
      .getFieldErrors()
      .stream()
      .findFirst()
      .map(e -> e.getField() + ": " + e.getDefaultMessage())
      .orElse("VALIDATION_ERROR");
    return new ErrorResponse("BAD_REQUEST", message);
  }

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<ErrorResponse> handleResponseStatus(ResponseStatusException ex) {
    int status = ex.getStatusCode().value();
    String code = status == 404 ? "NOT_FOUND" : status == 400 ? "BAD_REQUEST" : "ERROR";
    String message = ex.getReason() != null ? ex.getReason() : code;
    return ResponseEntity
      .status(ex.getStatusCode())
      .body(new ErrorResponse(code, message));
  }

  public record ErrorResponse(String error, String message) {}
}