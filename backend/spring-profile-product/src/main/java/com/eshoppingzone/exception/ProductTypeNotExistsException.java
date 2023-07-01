package com.eshoppingzone.exception;

public class ProductTypeNotExistsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
  public ProductTypeNotExistsException(String message) {
		
		super(message);
		this.message=message;
	}
		
		
		
	public ProductTypeNotExistsException() {
		
	}
}
