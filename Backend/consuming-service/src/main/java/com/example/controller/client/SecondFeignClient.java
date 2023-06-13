package com.example.controller.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "http://localhost:8002/apptwo",name="secondFeignClient")
public interface SecondFeignClient {

	@GetMapping("/second")
	public String getSecondMessage();
	
	
}
