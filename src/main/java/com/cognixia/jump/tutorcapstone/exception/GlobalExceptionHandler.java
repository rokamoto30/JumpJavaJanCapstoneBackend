package com.cognixia.jump.tutorcapstone.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND) // will add documentation for 404 error we get when exception is thrown
	public ResponseEntity<?> resourceNotFount(ResourceNotFoundException ex, WebRequest request) {
		
		// what data be returned back in response when exception thrown
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		
		// constructing the response
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

}