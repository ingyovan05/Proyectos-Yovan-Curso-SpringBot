package com.yovan.springboot.di.app.springboot_di.repositories;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yovan.springboot.di.app.springboot_di.models.product;

public class ProductRepositoryjson implements ProductRepository {

  private List<product> list;

  public ProductRepositoryjson() {
    Resource resource = new ClassPathResource("json/product.json");
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      list = Arrays.asList(objectMapper.readValue(resource.getInputStream(), product[].class));
    }  catch (IOException e) {
      e.printStackTrace();
    }

  }

  @Override
  public List<product> findAll() {
    return list;
  }

  @Override
  public product findById(Long id) {
    return list.stream().filter(p -> {
      return p.getId().equals(id);}).findFirst().orElseThrow();
  }

}
