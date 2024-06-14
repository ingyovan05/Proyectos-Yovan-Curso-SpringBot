package com.yovan.springboot.di.app.springboot_di.repositories;

import java.util.List;

import com.yovan.springboot.di.app.springboot_di.models.product;

public interface ProductRepository {

  List<product> findAll();

  product findById(Long id);

}
