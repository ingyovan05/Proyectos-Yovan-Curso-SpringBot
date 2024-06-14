package com.yovan.curso.springboot.app.jpa.springboot_jpa.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.yovan.curso.springboot.app.jpa.springboot_jpa.ProductValidation;
import com.yovan.curso.springboot.app.jpa.springboot_jpa.entities.Product;
import com.yovan.curso.springboot.app.jpa.springboot_jpa.services.ProductService;

import jakarta.validation.Valid;

@CrossOrigin(originPatterns = "*") // Donde esta el front, para este caso cualquiera
@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  private ProductService service;

  // @Autowired
  // private ProductValidation validation;

  @GetMapping
  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  public List<Product> list() {
    return service.findAll();
  }

  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  @GetMapping("/{id}")
  public ResponseEntity<?> view(@PathVariable Long id) {
    Optional<Product> productOptional = service.findById(id);
    if (productOptional.isPresent()) {
      return ResponseEntity.ok(productOptional.orElseThrow());
    }
    return ResponseEntity.notFound().build();
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result) {
    // validation.validate(product, result);
    if (result.hasFieldErrors()) {
      return validation(result);
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/{id}")
  public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result,
      @PathVariable Long id) {
    // validation.validate(product, result);
    if (result.hasFieldErrors()) {
      return validation(result);
    }
    Optional<Product> productOptional = service.update(id, product);
    if (productOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
    }
    return ResponseEntity.notFound().build();
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<Product> productOptional = service.delete(id);
    if (productOptional.isPresent()) {
      return ResponseEntity.ok(productOptional.orElseThrow());
    }
    return ResponseEntity.notFound().build();
  }

  private ResponseEntity<?> validation(BindingResult result) {

    Map<String, String> errors = new HashMap<>();
    result.getFieldErrors().forEach(err -> {
      errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
    });

    return ResponseEntity.badRequest().body(errors);
  }

}
