package com.stg.exception;

public class UserException extends RuntimeException {
	
	public UserException() {
	}

	private String message;

	public UserException(String message) {

		this.message = message;

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
