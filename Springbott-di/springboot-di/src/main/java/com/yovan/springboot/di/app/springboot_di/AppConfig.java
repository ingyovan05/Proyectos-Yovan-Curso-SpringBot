package com.yovan.springboot.di.app.springboot_di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.yovan.springboot.di.app.springboot_di.repositories.ProductRepository;
import com.yovan.springboot.di.app.springboot_di.repositories.ProductRepositoryjson;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {
  @Bean("productJSON")
  ProductRepository productRepositoryJson() {
    return new ProductRepositoryjson();
  }
}
