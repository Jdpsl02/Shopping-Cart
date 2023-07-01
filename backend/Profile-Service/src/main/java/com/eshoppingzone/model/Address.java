package com.eshoppingzone.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*getters, setters, all arguments and no argument constructors by annotations*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

/*@document- to specify custom property values*/
/*Defining collection name of mongodb to the model class*/

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
