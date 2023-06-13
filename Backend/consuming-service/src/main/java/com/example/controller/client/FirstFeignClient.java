package com.example.controller.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "http://localhost:8001/appone",name="firstFeignClient")
public interface FirstFeignClient {

	@GetMapping("/first")
	public String getFirstMessage();
	
	
}
