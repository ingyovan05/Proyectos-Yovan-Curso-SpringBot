package com.yovan.springboot.di.app.springboot_di.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yovan.springboot.di.app.springboot_di.models.product;
import com.yovan.springboot.di.app.springboot_di.services.ProductService;


@RestController
@RequestMapping("/api")

public class SomeController {

  @Autowired
  private ProductService service;

  
  @GetMapping
  public List<product> list() {
    return service.findAll();

  }

  @GetMapping("/{id}")
  public product show(@PathVariable Long id) {
    return service.findById(id);
  }

}
