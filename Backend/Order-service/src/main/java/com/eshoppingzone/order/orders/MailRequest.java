package com.eshoppingzone.order.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailRequest {
	
	 private String toEmail;
	 private String body;
	 private String subject;
	 private String attachment;

}
