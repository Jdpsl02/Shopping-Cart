package com.eshoppingzone.exception;

public class ProfileAlreadyExistsException extends RuntimeException{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	  public ProfileAlreadyExistsException(String message) {
		  
		super(message);
		this.message=message;
	}
	
	public ProfileAlreadyExistsException() {
		
	}

}
