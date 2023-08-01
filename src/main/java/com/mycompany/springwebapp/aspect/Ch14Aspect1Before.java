package com.mycompany.springwebapp.aspect;

import org.apache.logging.log4j.core.config.Order;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Order(2)
@Slf4j
public class Ch14Aspect1Before {
   @Before("execution(public * com.mycompany.springwebapp.controller.Ch14Controller.before*(..))") //(..) 괄호안에 뭐가됫든 상관없다
   public void method() {
      log.info("실행");
   }
}