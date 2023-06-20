package com.eshoppingzone.orders.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
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
	private String State;
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Integer getFlatNumber() {
		return flatNumber;
	}
	public void setFlatNumber(Integer flatNumber) {
		this.flatNumber = flatNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getPincode() {
		return pincode;
	}
	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}
	public Address(Integer customerId, String fullName, String mobileNumber, Integer flatNumber, String city,
			Integer pincode, String state) {
		super();
		this.customerId = customerId;
		this.fullName = fullName;
		this.mobileNumber = mobileNumber;
		this.flatNumber = flatNumber;
		this.city = city;
		this.pincode = pincode;
		State = state;
	}
	
	public Address() {
		
	}
	
	

	

}