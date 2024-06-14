package com.yovan.curso.springboot.app.jpa.springboot_jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.yovan.curso.springboot.app.jpa.springboot_jpa.entities.Product;

public interface ProductRepository extends CrudRepository< Product , Long> {

  boolean existsBySku(String sku); 

}
