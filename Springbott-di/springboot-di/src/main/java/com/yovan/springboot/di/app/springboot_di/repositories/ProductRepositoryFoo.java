package com.yovan.springboot.di.app.springboot_di.repositories;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yovan.springboot.di.app.springboot_di.models.product;



@Repository("productoFoo")
public class ProductRepositoryFoo implements ProductRepository {

  @Override
  public List<product> findAll() {
    return Collections.singletonList(new product(1L,"Monitor Asus 27",600L));
  }

  @Override
  public product findById(Long id) {
    return new product(id,"Monitor Asus 27",600L);
  }
  

}
