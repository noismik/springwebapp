package com.mycompany.springwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestController {
	//http://localhost:8080/springwebapp
	@RequestMapping("/")
	public String index() {
		
		log.info("test");
		log.info("잘되니?");
		return "index";
		
		
	}
}
