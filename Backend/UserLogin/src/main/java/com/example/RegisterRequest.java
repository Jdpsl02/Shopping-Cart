package com.example;

import com.example.Users.Role;

public class RegisterRequest {
	private String username;
	private String password;
	private String role;
	
public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	//	public Role getRole() {
//		return role;
//	}
//	public void setRole(String role) {
//		this.role = role;
//	}
	public RegisterRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RegisterRequest(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role=role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
}	

