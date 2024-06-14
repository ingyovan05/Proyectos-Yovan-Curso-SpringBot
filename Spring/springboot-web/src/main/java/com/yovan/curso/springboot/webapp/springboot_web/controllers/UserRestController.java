package com.yovan.curso.springboot.webapp.springboot_web.controllers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yovan.curso.springboot.webapp.springboot_web.controllers.models.User;
import com.yovan.curso.springboot.webapp.springboot_web.controllers.models.dto.UserDto;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")

public class UserRestController {

  @GetMapping("/details")

  public UserDto details() {

    UserDto userDto = new UserDto();
    User user = new User("Yovan", "Solano");

    userDto.setUser(user);
    userDto.setTitle("Hola Mundo Spring Boot");

    return userDto;
  }

  @GetMapping("/list")

  public List<User> list() {
    User user1 = new User("X11", "Y1");
    User user2 = new User("X2", "Y2");
    User user3 = new User("X3", "Y3");

    // List<User> users = new ArrayList<>();
    // users.add(user1);
    // users.add(user2);
    // users.add(user3);

    List<User> users = Arrays.asList(user1, user2, user3);

    return users;

  }

  @GetMapping("/details-2")
  public Map<String, Object> detailsMap() {
    User user = new User("Yovan", "Solano");

    Map<String, Object> body = new HashMap<>();
    body.put("title", "Hola Mundo Spring Boot");
    // body.put("name", "Yovan");
    body.put("user", user);
    return body;
  }

}
