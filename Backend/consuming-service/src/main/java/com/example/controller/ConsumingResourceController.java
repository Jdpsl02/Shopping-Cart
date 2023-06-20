package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.controller.client.FirstFeignClient;
import com.example.controller.client.SecondFeignClient;


@RestController
@RequestMapping("/appthree")
@EnableFeignClients(basePackages = "com.example.controller.client")
public class ConsumingResourceController {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	FirstFeignClient firstClient;
	@Autowired
	SecondFeignClient secondClient;
	
	@Autowired
	WebClient.Builder builder;
	
	
	@GetMapping("/message")
	public String getMessage() {
		
	//	String first=restTemplate.getForObject("http://localhost:8001/appone/first", String.class);
	//	String second=restTemplate.getForObject("http://localhost:8002/apptwo/second", String.class);
		
		
//		String first=firstClient.getFirstMessage();
//		String second=secondClient.getSecondMessage() +" From Feign Client";
	
		String first=builder.build()
				.get()
				.uri("http://localhost:8001/appone/first")
				.retrieve()
				.bodyToMono(String.class)
				.block();
		
		String second=builder.build()
				.get()
				.uri("http://localhost:8002/apptwo/second")
				.retrieve()
				.bodyToMono(String.class)
				.block();
		second=second+" from web client";
		
		return first+" "+second;
	}
}