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
    if (service == null) {
      return true;
    }

    return !service.existsBySku(value);
  }

}
