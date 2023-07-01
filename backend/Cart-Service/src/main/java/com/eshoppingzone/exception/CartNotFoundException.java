package com.eshoppingzone.exception;

public class CartNotFoundException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

	public CartNotFoundException(String message) {
		super();
		this.message = message;
	}

	public CartNotFoundException() {
		
	}
	

}
