package com.eshoppingzone.config;

import java.util.HashMap; 
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.eshoppingzone.exception.CartAlreadyExistsException;
import com.eshoppingzone.exception.CartNotFoundException;
import com.eshoppingzone.resource.CartResource;


@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {
	
	Logger logger= LoggerFactory.getLogger(ValidationHandler.class);
	
	
	// global validation handler  for each validated field
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		
			Map<String,String> errors= new HashMap<>();
			ex.getBindingResult().getAllErrors().forEach((error)->{
			
			String fieldName = ((FieldError)error).getField();
			String message=error.getDefaultMessage();
			errors.put(fieldName, message);
			
		});
			
		return new ResponseEntity<Object>(errors,HttpStatus.BAD_REQUEST);
	}
	
	//exception handler for cart not found
	@ExceptionHandler(value=CartNotFoundException.class)
	public ResponseEntity<String> cartNotFoundException(CartNotFoundException profileNotFoundException) {
		
		logger.error("cart not found ");
		return new ResponseEntity<>("cart not found",HttpStatus.NOT_FOUND);
		
	}
	//exception handler for cart already exists
	@ExceptionHandler(value=CartAlreadyExistsException.class)
	public ResponseEntity<String> cartAlreadyExistsException(CartAlreadyExistsException profileAlreadyExistsException) {
		
		logger.error("cart Alredy exists ");
		return new ResponseEntity<>("cart Already Exists",HttpStatus.CONFLICT);
		
	}
}
