package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.*"})
@EnableFeignClients("com.example")
public class AuthAuthorizationMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthAuthorizationMsApplication.class, args);
	}
}