package com.cognixia.jump.tutorcapstone.exception;

public class ResourceNotFoundException extends Exception{
    private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String msg) {
		// calls the Exception(String msg) constructor
		super(msg);
	}
}
