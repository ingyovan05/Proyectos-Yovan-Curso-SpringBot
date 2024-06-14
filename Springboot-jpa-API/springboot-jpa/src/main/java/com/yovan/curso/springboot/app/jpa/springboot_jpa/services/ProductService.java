package com.yovan.curso.springboot.app.jpa.springboot_jpa.services;

import java.util.List;
import java.util.Optional;

import com.yovan.curso.springboot.app.jpa.springboot_jpa.entities.Product;

public interface ProductService {

  List<Product> findAll();

  Optional<Product> findById(Long Id);

  Product save(Product product);

  Optional<Product> update(Long id, Product product);

  Optional<Product> delete(Long id);

  boolean existsBySku(String sku); 

}
