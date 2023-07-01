
package com.eshoppingzone.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*getters, setters, all arguments and no argument constructors by annotations*/
@Data
@AllArgsConstructor
@NoArgsConstructor
/*@document- to specify custom property values*/
/*Defining collection name of mongodb to the model class*/
@Document(collection = "UserProfile")
public class UserProfile {
	
	 /*This helps ignoring the field by not mapping it to the database*/
	@Transient
	public  static final String SEQUENCE_NAME="ProfileSequence";
	
	/*Providing all variables required */
    /*Setting up the below variable as primary key in the collection*/
	@Id
	private int profileId;
	
	@NotEmpty(message="Name is empty")
	private String fullName;
	
	private String image;
	
	
	@Email(message = "Email is not valid")
	@NotEmpty(message = "Email cannot be empty")
	@Indexed(unique=true)
	private String emailId;
	
	@NotNull
	private Long mobileNumber;
	private String about;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	private String gender;
	@NotEmpty
	private  String role;
	
	

	@NotBlank
	@Size(min=8,message="length must be greater than 8")
	private String password;


	private List<Address> address;
	
	

	

	
	
}
