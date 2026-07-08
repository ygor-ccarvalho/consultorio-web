package com.ygor.web_consultorio.service.exception;

public class DataBindingViolationException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public DataBindingViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataBindingViolationException(String message) {
		super(message);
	}
}
