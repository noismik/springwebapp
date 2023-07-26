package com.mycompany.springwebapp.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mycompany.springwebapp.dao.Ch12DaoByAnnotation1;
import com.mycompany.springwebapp.dao.Ch12DaoByAnnotation2;
import com.mycompany.springwebapp.dao.Ch12DaoByAnnotation3;
import com.mycompany.springwebapp.dao.Ch12DaoI;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class Ch12ServiceDiByAnnotation {
   //필드
   @Autowired //@Resource
   private Ch12DaoByAnnotation1 daoannotation1;
   
   private Ch12DaoByAnnotation2 daoannotation2;
   private Ch12DaoByAnnotation3 daoannotation3;
   
   @Resource(name="ch12DaoImpl1")
   //@Autowired @Qualifier("ch12DapImpl1")
   private Ch12DaoI ch12Dao;
   //생성자
   public Ch12ServiceDiByAnnotation() {
      log.info("실행1");
   }
   //생성자
   //@Autowired
   public Ch12ServiceDiByAnnotation(Ch12DaoByAnnotation1 daoByAnnotation1) {
      log.info("실행2");
      this.daoannotation1 = daoByAnnotation1;
   }
   
   //setter
   public void setCh12DaoByAnotation1(Ch12DaoByAnnotation1 daoByAnnotation1) {
      log.info("실행");
      this.daoannotation1 = daoByAnnotation1;
   }
   @Autowired //@Resource
   public void setCh12DaoByAnotation2(Ch12DaoByAnnotation2 daoByAnnotation2) {
      log.info("실행");
      this.daoannotation2 = daoByAnnotation2;
   }
   @Autowired //@Resource
   public void setCh12DaoByAnotation3(Ch12DaoByAnnotation3 daoByAnnotation3) {
      log.info("실행");
      this.daoannotation3 = daoByAnnotation3;
   }
   
   
   //인스턴스 메소드
   public void method() {
      daoannotation1.method();
      daoannotation2.method();
      daoannotation3.method();
      ch12Dao.method();
   }
}