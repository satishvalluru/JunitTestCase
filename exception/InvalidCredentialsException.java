package com.java.exception;

public class InvalidCredentialsException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidCredentialsException(String message) {
		super(message);

	}

	public InvalidCredentialsException(String message, Throwable t) {
		super(message, t);

	}

}
