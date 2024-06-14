package com.yovan.springboot.di.app.springboot_di.services;

import java.util.List;

import com.yovan.springboot.di.app.springboot_di.models.product;

public interface ProductService {

  List<product> findAll();

  product findById(Long id);

} 
