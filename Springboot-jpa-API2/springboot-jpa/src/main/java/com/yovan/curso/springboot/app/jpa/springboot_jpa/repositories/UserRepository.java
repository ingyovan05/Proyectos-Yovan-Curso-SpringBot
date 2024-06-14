package com.yovan.curso.springboot.app.jpa.springboot_jpa.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.yovan.curso.springboot.app.jpa.springboot_jpa.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

  boolean existsByUsername(String username);

  Optional<User> findByUsername(String username);

}
