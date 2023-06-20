
package security.pojo;

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
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
/*@document- to specify custom property values*/
/*Defining collection name of mongodb to the model class*/
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
	
	public UserProfile(int profileId, @NotEmpty(message = "Name is empty") String fullName, String image,
			@Email(message = "Email is not valid") @NotEmpty(message = "Email cannot be empty") String emailId,
			@NotNull Long mobileNumber, String about, LocalDate dateOfBirth, String gender, @NotEmpty String role,
			@NotBlank @Size(min = 8, message = "length must be greater than 8") String password,
			List<Address> address) {
		super();
		this.profileId = profileId;
		this.fullName = fullName;
		this.image = image;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
		this.about = about;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.role = role;
		this.password = password;
		this.address = address;
	}
	
	public UserProfile() {
	
	}
	



	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public void setAbout(String about) {
		this.about = about;
	}


	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setAddress(List<Address> address) {
		this.address = address;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}


	public int getProfileId() {
		return profileId;
	}


	public String getFullName() {
		return fullName;
	}


	public String getImage() {
		return image;
	}


	public Long getMobileNumber() {
		return mobileNumber;
	}


	public String getAbout() {
		return about;
	}


	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}


	public String getGender() {
		return gender;
	}


	public String getRole() {
		return role;
	}


	public String getPassword() {
		return password;
	}


	public List<Address> getAddress() {
		return address;
	}



	

	
	
}
