package com.yovan.springboot.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.yovan.springboot.di.app.springboot_di.models.product;
import com.yovan.springboot.di.app.springboot_di.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private Environment environment;
  // @Autowired
  // @Qualifier("productList")
  private ProductRepository repository;

  public ProductServiceImpl(@Qualifier("productJSON") ProductRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<product> findAll() {
    return repository.findAll().stream().map(p -> {
      System.out.println(environment.getProperty("config.price.tax", Double.class));
      @SuppressWarnings("null")
      Double priceTax = p.getPrice() * environment.getProperty("config.price.tax", Double.class);
      // product newProd = new product(p.getId(), p.getName(), priceTax.longValue());
      product newProd = (product) p.clone();
      newProd.setPrice(priceTax.longValue());
      return newProd;
      // p.setPrice(priceTax.longValue());
      // return p;
    }).collect(Collectors.toList());
  }

  @Override
  public product findById(Long id) {
    return repository.findById(id);
  }
}
