package com.yovan.curso.springboot.aop.springboot_app_aop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Aspect
@Component
public class GreetingFooAspect {
  private Logger logger = LoggerFactory.getLogger(getClass());

  @Before("GreetingServicePointCut.greetingFooLoggerPointCut()")
  public void loggerBefore(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();
    String args = Arrays.toString(joinPoint.getArgs());
    logger.info("1.1. Antes: " + method + " invocado con los argumentos " + args);

  }

  @After("GreetingServicePointCut.greetingFooLoggerPointCut()")
  public void loggerAfter(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();
    String args = Arrays.toString(joinPoint.getArgs());
    logger.info("2.1. Despues: " + method + " invocando con los argumentos " + args);

  }

}
