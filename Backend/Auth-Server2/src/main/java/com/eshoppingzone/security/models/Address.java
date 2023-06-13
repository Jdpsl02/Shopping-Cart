package com.eshoppingzone.security.models;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Address {
	
	@NotNull
	private int houseNumber;
	private String streetName;
	private String colonyName;
	private String city;
	private String state;
	
	@NotNull
	private int pincode;


	

}
