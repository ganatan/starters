package com.ganatan.starter;

import org.springframework.http.HttpStatus;
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

  @ExceptionHandler(ResponseStatusException.class)
  public ErrorResponse handleResponseStatus(ResponseStatusException ex) {
    return new ErrorResponse(
      ex.getStatusCode().toString(),
      ex.getReason() != null ? ex.getReason() : ex.getStatusCode().toString()
    );
  }

  public record ErrorResponse(String error, String message) {}
}



