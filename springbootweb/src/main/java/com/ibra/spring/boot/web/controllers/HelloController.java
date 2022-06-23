package com.ibra.spring.boot.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  //This creates a class controller
public class HelloController {
	
	@RequestMapping("/hello") //This allows the user to access this class or End-point from the web browser
	@ResponseBody            //This  annotation sends the response back in the HTTP body 
	public String hello(@RequestParam String name) {//This one allows the user to pass in a parameter to the web browser later on
		
		return "Hello" + name;
	}

}
