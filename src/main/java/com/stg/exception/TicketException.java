package com.stg.exception;

public class TicketException extends RuntimeException {
	public TicketException() {
	}

	private String message;

	public TicketException(String message) {

		this.message = message;

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
