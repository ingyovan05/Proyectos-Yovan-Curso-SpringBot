package com.yovan.curso.springboot.error.sreping_boot_error.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.yovan.curso.springboot.error.sreping_boot_error.exceptions.UserNotFoundException;
import com.yovan.curso.springboot.error.sreping_boot_error.models.Error;

@RestControllerAdvice
public class HandlerExceptionController {

  @ExceptionHandler({ ArithmeticException.class })
  public ResponseEntity<Error> divisionByZero(Exception ex) {
    Error error = new Error();
    error.setDate(new Date());
    error.setError("Error division por cero");
    error.setMessage(ex.getMessage());
    error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    return ResponseEntity.internalServerError().body(error);
  }

  @ExceptionHandler({ NumberFormatException.class })
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Map<String, Object> NumberFormatException(Exception ex) {
    Map<String, Object> error = new HashMap<>();
    error.put("date", new Date().toString());
    error.put("error", "numero invalido");
    error.put("message", ex.getMessage());
    error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
    return error;
  }

  @ExceptionHandler({ NullPointerException.class,
      HttpMessageNotWritableException.class,
    UserNotFoundException.class })
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Map<String, Object> userNotFoundException(Exception ex) {
    Map<String, Object> error = new HashMap<>();
    error.put("date", new Date().toString());
    error.put("error", "El usuario es invalido");
    error.put("message", ex.getMessage());
    error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
    return error;
  }

  @ExceptionHandler(NoHandlerFoundException.class)

  public ResponseEntity<Error> notFoundEx(NoHandlerFoundException e) {
    Error error = new Error();
    error.setDate(new Date());
    error.setError("Api rest no encontrado");
    error.setMessage(e.getMessage());
    error.setStatus(HttpStatus.NOT_FOUND.value());
    return ResponseEntity.status(404).body(error);
  }

}
