package com.yovan.curso.springboot.webapp.springboot_web.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.yovan.curso.springboot.webapp.springboot_web.controllers.models.User;

@Controller

public class UserController {

  @GetMapping("/details")
  // public String detail(Map<String,Object> model){
  // model.put("title", "Hola Mundo Spring Boot");
  // model.put("name", "Yovan");
  // model.put("lastname", "Solano1");
  // return "details";
  // }

  public String detail(Model model) {

    model.addAttribute("title", "Hola Mundo Spring Boot");
    model.addAttribute("name", "Yovan");
    model.addAttribute("lastname", "Solano");
    model.addAttribute("email", "ingyovansolano@gmail.com");

    return "details";
  }

  @GetMapping("/list")
  public String list(ModelMap model) {  
    model.addAttribute("title", "Hola Mundo Spring Boot if");
    return "list";
  }

  @ModelAttribute("users")
  public List<User> usersModel (){
    List<User> users = Arrays.asList(new User("X1", "Y1"),
    new User("X2", "Y2"),
    new User("X3", "Y3"),
    new User("X4", "Y4", "ingyovan@hotmail.com"));
    return users;
  }

}
