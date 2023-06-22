package com.eshoppingzone.ewallet.models;

import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document("Ewallet")
public class Ewallet {
	
	@Id
	private String walletId;
	
	private int profileId;
	private double currentBalance;
	
	
	
	
	public Ewallet(int profileId, double currentBalance) {
		super();
		this.profileId = profileId;
		this.currentBalance = currentBalance;
		
	}
	
	public Ewallet() {
		
	}
	
	
	
	
	
	public String getWalletId() {
		return walletId;
	}
	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}
	public int getProfileId() {
		return profileId;
	}
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	public double getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}
	
	
	
	
	
	
	
		
}