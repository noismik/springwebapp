package com.mycompany.springwebapp.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j

public class Ch14Aspect5AfterThrowing {
   @AfterThrowing(
		   pointcut="execution(public * com.mycompany.springwebapp.controller.Ch14Controller.afterThrowing*(..))", //(..) 괄호안에 뭐가됫든 상관없다
   		   throwing="e")
   public void method(Throwable e) {
      log.info("실행");
      log.info("예외 메시지: " + e.getMessage());
   }
}