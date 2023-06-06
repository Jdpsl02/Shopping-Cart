package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appone")
public class FirstResourceController {

	@Autowired
	Environment	env;
	
	@GetMapping("/first")
	public String getFirstMessage() {
		return env.getProperty("local.server.port")+" Hello";
	}
}