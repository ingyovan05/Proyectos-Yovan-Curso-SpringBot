package com.yovan.curso.springboot.error.sreping_boot_error.services;

import java.util.List;
import java.util.Optional;

import com.yovan.curso.springboot.error.sreping_boot_error.models.domain.User;

public interface UserService {

  
  List<User> findAll();
  Optional <User> findById(Long id);
  
} 