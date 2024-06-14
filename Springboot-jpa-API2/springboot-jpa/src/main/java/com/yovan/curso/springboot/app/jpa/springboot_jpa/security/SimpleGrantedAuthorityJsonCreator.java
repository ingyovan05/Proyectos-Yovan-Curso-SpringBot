package com.yovan.curso.springboot.app.jpa.springboot_jpa.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleGrantedAuthorityJsonCreator {

  @JsonCreator
  public SimpleGrantedAuthorityJsonCreator(@JsonProperty("authority") String role){}
}

