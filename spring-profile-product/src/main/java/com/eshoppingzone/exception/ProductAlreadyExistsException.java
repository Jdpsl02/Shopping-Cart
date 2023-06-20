package com.eshoppingzone.exception;

public class ProductAlreadyExistsException extends RuntimeException{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	  public ProductAlreadyExistsException(String message) {
		  
		super(message);
		this.message=message;
	}
	
	public ProductAlreadyExistsException() {
		
	}

}
