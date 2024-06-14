package com.yovan.curso.springboot.aop.springboot_app_aop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GreetingServicePointCut {

  @Pointcut("execution(*  com.yovan.curso.springboot.aop.springboot_app_aop.services.*.*(..))")
  public void greetingLoggerPointCut() {
  }

  @Pointcut("execution(*  com.yovan.curso.springboot.aop.springboot_app_aop.services.*.*(..))")
  public void greetingFooLoggerPointCut() {
  }

}
