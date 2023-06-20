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
import com.eshoppingzone.exception.ProfileAlreadyExistsException;
import com.eshoppingzone.exception.ProfileNotFoundException;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {
	
	Logger logger= LoggerFactory.getLogger(ValidationHandler.class);
	
	//validation handler for handling error message when validation fails
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

	//handles exception if profile not found
	@ExceptionHandler(value=ProfileNotFoundException.class)
	public ResponseEntity<String> profileNotFoundException(ProfileNotFoundException profileNotFoundException) {
		logger.error("profile not found");
		return new ResponseEntity<String>("profile not found",HttpStatus.NOT_FOUND);
		
	}
	
	//handles exception if profile already found
	@ExceptionHandler(value=ProfileAlreadyExistsException.class)
	public ResponseEntity<String> profileAlreadyExistsException(ProfileAlreadyExistsException profileAlreadyExistsException) {
		 
		logger.error("profile already exists");
		return new ResponseEntity<>("profile Already Exists",HttpStatus.CONFLICT);
		
	}
}
