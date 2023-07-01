package com.eshoppingzone.mailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class EmailService {
	
	 @Autowired 
	 private JavaMailSender javaMailSender;
	 
	 @Value("${spring.mail.username}") 
	 private String sender;
	
	public String sendSimpleMail(String email,String body,String Subject) {
		SimpleMailMessage mailMessage
        = new SimpleMailMessage();
		
    mailMessage.setFrom(sender);
    mailMessage.setTo(email);
    mailMessage.setText(body);
    mailMessage.setSubject(Subject);

    // Sending the mail
    javaMailSender.send(mailMessage);
    
    return "sending";

	}

}
