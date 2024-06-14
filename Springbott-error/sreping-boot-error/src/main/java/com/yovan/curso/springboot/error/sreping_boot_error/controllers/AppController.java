package com.yovan.curso.springboot.error.sreping_boot_error.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yovan.curso.springboot.error.sreping_boot_error.models.domain.User;
import com.yovan.curso.springboot.error.sreping_boot_error.services.UserService;

@RestController
@RequestMapping("/app")
public class AppController {

  @Autowired
  private UserService service;

  @GetMapping
  public String index() {
    // int value = 100/0;

    int value = Integer.parseInt("10");

    System.out.println(value);
    return "ok 200";
  }

  @GetMapping("/show/{id}")
  public ResponseEntity<?> show(@PathVariable(name = "id") Long id) {
    // User user = service.findById(id).orElseThrow(() -> new
    // UserNotFoundException("Usuario no existe"));
    // if (user == null) {
    // throw new UserNotFoundException("Usuario no existe");
    // }
    Optional<User> Optionaluser = service.findById(id);
    if (Optionaluser.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    // System.out.println(user.getName());
    return ResponseEntity.ok(Optionaluser.get());
  }

}
