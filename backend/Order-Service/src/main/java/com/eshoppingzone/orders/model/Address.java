package com.eshoppingzone.orders.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="OrderAddress")
//Storing address for order
public class Address {
	
	//generating sequence for customer id
	@Transient
	public  static final String SEQUENCE_NAME="CustomerSequence";
	
	
	@Id
	private Integer customerId;
	private String fullName;
	private String mobileNumber;
	private Integer flatNumber;
	private String city;
	private Integer pincode;
	private String state;
	
	

	

}