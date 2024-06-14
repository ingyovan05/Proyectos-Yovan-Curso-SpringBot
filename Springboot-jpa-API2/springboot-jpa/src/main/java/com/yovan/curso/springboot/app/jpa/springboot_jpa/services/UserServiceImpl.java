package com.yovan.curso.springboot.app.jpa.springboot_jpa.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yovan.curso.springboot.app.jpa.springboot_jpa.entities.Role;
import com.yovan.curso.springboot.app.jpa.springboot_jpa.entities.User;
import com.yovan.curso.springboot.app.jpa.springboot_jpa.repositories.RoleRepository;
import com.yovan.curso.springboot.app.jpa.springboot_jpa.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository repository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public List<User> finAll() {
    return (List<User>) repository.findAll();
  }

  @Override
  @Transactional
  public User save(User user) {
    Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_USER");
    List<Role> roles = new ArrayList<>();
    optionalRoleUser.ifPresent(roles::add);

    if (user.isAdmin()) {
      Optional<Role> optinalToleAdmien = roleRepository.findByName("ROLE_ADMIN");
      optinalToleAdmien.ifPresent(roles::add);
    }

    user.setRoles(roles);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return repository.save(user);
  }

  @Override
  public boolean existsByUsername(String username) {
    return repository.existsByUsername(username);
  }

}
