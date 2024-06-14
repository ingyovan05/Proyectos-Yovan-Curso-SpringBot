package com.yovan.curso.springboot.aop.springboot_app_aop.services;


public interface GreetingService {
  String sayHello(String person, String phrase);
  String sayHelloError(String person, String phrase);
}
