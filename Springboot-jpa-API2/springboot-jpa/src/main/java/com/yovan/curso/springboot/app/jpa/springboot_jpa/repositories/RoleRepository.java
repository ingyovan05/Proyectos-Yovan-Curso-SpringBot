package com.yovan.curso.springboot.app.jpa.springboot_jpa.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.yovan.curso.springboot.app.jpa.springboot_jpa.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

  Optional<Role> findByName(String name);

}
