package com.yovan.curso.springboot.aop.springboot_app_aop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Aspect
@Component
public class GreetingAspect {

  private Logger logger = LoggerFactory.getLogger(this.getClass());


  @Before("GreetingServicePointCut.greetingLoggerPointCut()")
  public void loggerBefore(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();
    String args = Arrays.toString(joinPoint.getArgs());
    logger.info("1. Antes: " + method + " con los argumentos " + args);

  }

  @After("GreetingServicePointCut.greetingLoggerPointCut()")
  public void loggerAfter(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();
    String args = Arrays.toString(joinPoint.getArgs());
    logger.info("2. Despues: " + method + " con los argumentos " + args);

  }

  @AfterThrowing("GreetingServicePointCut.greetingLoggerPointCut()")
  public void loggerThrowing(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();
    String args = Arrays.toString(joinPoint.getArgs());
    logger.info("3. Throwing: " + method + " con los argumentos " + args);
  }

  @AfterReturning("GreetingServicePointCut.greetingLoggerPointCut()")
  public void loggerReturning(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();
    String args = Arrays.toString(joinPoint.getArgs());
    logger.info("4. Returning: " + method + " con los argumentos " + args);
  }

  @Around("GreetingServicePointCut.greetingLoggerPointCut()")
  public Object loggerAround(ProceedingJoinPoint joinPoint) throws Throwable {
    String method = joinPoint.getSignature().getName();
    String args = Arrays.toString(joinPoint.getArgs());
    Object result = null;
    try {
      logger.info("5. Antes en Around : " + method + " con los argumentos " + args);
      result = joinPoint.proceed();
      logger.info("6. Despues en Around : " + method + " () retorna) " + result);
      return result;
    } catch (Throwable e) {
      logger.info("7. error en la llamada de : " + method);
      throw e;
    }
  }
}
