package com.eshoppingzone.security.payload.request;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.*;

import com.eshoppingzone.security.models.Address;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
 
@Data
@AllArgsConstructor
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String fullName;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String emailId;
    
    private Set<String> roles;
    
    private String image;
    @NotNull
	private Long mobileNumber;
	private String about;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	private String gender;
    
    @NotBlank
    @Size(min = 8, max = 40)
    private String password;
    
    private List<Address> address;
  
    public String getFullName() {
        return fullName;
    }
 
    public void setUsername(String fullName) {
        this.fullName = fullName;
    }
 
    public String getEmailId() {
        return emailId;
    }
 
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Set<String> getRoles() {
      return this.roles;
    }
    
    public void setRole(Set<String> roles) {
      this.roles = roles;
    }
    
    
}
