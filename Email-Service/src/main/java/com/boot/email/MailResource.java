package com.boot.email;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MailResource {
	
	Logger logger= LoggerFactory.getLogger(MailResource.class);
	
	@Autowired
	private EmailSenderService service;
	
	@PostMapping("/send")
	public String triggerMail( @RequestBody MailRequest mailRequest) throws MessagingException {
		logger.info("method hit"+mailRequest);
		service.sendEmailWithAttachment(mailRequest.getToEmail(), mailRequest.getBody(), mailRequest.getSubject(),mailRequest.getAttachment());
		return "Message send to "+mailRequest.getToEmail();
	}
	
}
