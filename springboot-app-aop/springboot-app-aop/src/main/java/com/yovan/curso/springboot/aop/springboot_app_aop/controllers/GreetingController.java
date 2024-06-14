package com.yovan.curso.springboot.aop.springboot_app_aop.controllers;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yovan.curso.springboot.aop.springboot_app_aop.services.GreetingService;

@RestController
public class GreetingController {

  @Autowired
  private GreetingService greetingservice;

  
  @GetMapping("/greeting")

  public ResponseEntity<?> greeting() {

    return ResponseEntity.ok(Collections.singletonMap("gretting",
        greetingservice.sayHello("Yovan", "Hola que tal")));


  }
        
  @GetMapping("/greeting-error")
  public ResponseEntity<?> greetingError() {

    return ResponseEntity.ok(Collections.singletonMap("gretting",
        greetingservice.sayHelloError("Yovan", "Hola que tal")));       

  }
  
}
