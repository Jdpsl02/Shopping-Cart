package com.boot.email;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@SpringBootApplication
public class EmailServiceApplication {
	
	@Autowired
	private EmailSenderService service;
	
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);
	}
	
//	@EventListener(ApplicationReadyEvent.class)
//	public void triggerMail() throws MessagingException {
////		service.sendSimpleEmail("shivamgupta846023@gmail.com", "this is mail from spring boot", "email integration" );
//		
//		service.sendEmailWithAttachment("shivamgupta846023@gmail.com", "this is email with attachment", "attachment", "C:\\Users\\ARROW\\Downloads\\detailCASESTUDY.txt");
//	}

}
