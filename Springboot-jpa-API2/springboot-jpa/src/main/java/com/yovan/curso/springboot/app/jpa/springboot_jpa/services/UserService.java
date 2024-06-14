package com.yovan.curso.springboot.app.jpa.springboot_jpa.services;

import java.util.List;

import com.yovan.curso.springboot.app.jpa.springboot_jpa.entities.User;

public interface UserService {

  List<User> finAll();

  User save(User user);

  boolean existsByUsername(String username);

}