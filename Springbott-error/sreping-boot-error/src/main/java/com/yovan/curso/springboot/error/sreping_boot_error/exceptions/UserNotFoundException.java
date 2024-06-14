package com.yovan.curso.springboot.error.sreping_boot_error.exceptions;


public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException  (String message){
    super (message);
  }

}
