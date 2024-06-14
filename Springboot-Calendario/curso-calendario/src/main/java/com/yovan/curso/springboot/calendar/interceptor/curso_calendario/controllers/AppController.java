package com.yovan.curso.springboot.calendar.interceptor.curso_calendario.controllers;

import java.util.Map;
import java.util.Date;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class AppController {
  @GetMapping("/foo")
  public ResponseEntity<?> foo(HttpServletRequest request) {
    Map<String, Object> data = new HashMap<>();
    data.put("title", "Bienvenidos al sistema de atención");
    data.put("Date", new Date());
    data.put("message", request.getAttribute("message"));
    return ResponseEntity.ok(data);
  }
}
