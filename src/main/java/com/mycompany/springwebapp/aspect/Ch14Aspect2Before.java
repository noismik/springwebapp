package com.mycompany.springwebapp.aspect;

import org.apache.logging.log4j.core.config.Order;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
@Order(1)
public class Ch14Aspect2Before {
   @Before("execution(public * com.mycompany.springwebapp.controller.Ch14Controller.before*(..))") //(..) 괄호안에 뭐가됫든 상관없다
   public void method() {
      log.info("실행");
   }
}