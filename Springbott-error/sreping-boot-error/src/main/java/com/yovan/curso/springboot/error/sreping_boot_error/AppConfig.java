package com.yovan.curso.springboot.error.sreping_boot_error;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yovan.curso.springboot.error.sreping_boot_error.models.domain.User;

@Configuration
public class AppConfig {

  @Bean
  List<User> users() {
    List<User> users = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      String name = "Pepe " + String.valueOf(i);
      String lastname = "Gonzales " + String.valueOf(i);
      users.add(new User(Long.valueOf(i), name, lastname));
    }
    return users;
  }

}
