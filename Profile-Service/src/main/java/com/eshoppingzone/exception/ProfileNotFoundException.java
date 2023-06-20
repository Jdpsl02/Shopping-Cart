package com.eshoppingzone.exception;

public class ProfileNotFoundException extends RuntimeException{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public  ProfileNotFoundException(String message) {
		
		super(message);
		this.message=message;
	}
	
	public ProfileNotFoundException() {
		
	}
}
