package com.eshoppingzone.security.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.tomcat.jni.Address;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
	
	
	
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
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dateOfBirth;
	private String gender;
	
	private Set<Role> role;
	
	
	
	@NotBlank
	@Size(min=8,message="length must be greater than 8")
	private String password;


	

	
	
}
