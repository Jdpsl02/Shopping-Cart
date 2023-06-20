package com.eshoppingzone.exception;

public class CartAlreadyExistsException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

	public CartAlreadyExistsException(String message) {
		super();
		this.message = message;
	}

	public CartAlreadyExistsException() {
		
	}
	
	

}
