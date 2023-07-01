package security.pojo;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*getters, setters, all arguments and no argument constructors by annotations*/
@Data
//@AllArgsConstructor
//@NoArgsConstructor
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

	public Address(@NotNull int houseNumber, String streetName, String colonyName, String city, String state,
			@NotNull int pincode) {
		super();
		this.houseNumber = houseNumber;
		this.streetName = streetName;
		this.colonyName = colonyName;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	
	public Address() {
	
	}


	public int getHouseNumber() {
		return houseNumber;
	}


	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}


	public String getStreetName() {
		return streetName;
	}


	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}


	public String getColonyName() {
		return colonyName;
	}


	public void setColonyName(String colonyName) {
		this.colonyName = colonyName;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public int getPincode() {
		return pincode;
	}


	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	
	
	

	

	

}
