package com.yovan.curso.springboot.app.jpa.springboot_jpa.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yovan.curso.springboot.app.jpa.springboot_jpa.services.ProductService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class IsExistsDbValidation implements ConstraintValidator<IsExistsDb, String> {

  @Autowired
  private ProductService service;

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    System.out.println("Entro a is Valid");
    System.out.println("Valor" + value);
    return  !service.existsBySku(value);
  }

}
