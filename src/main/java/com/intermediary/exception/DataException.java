package com.intermediary.exception;

import org.springframework.http.HttpStatus;

public class DataException extends RuntimeException {/**
	 * 
	 */
	private static final long serialVersionUID = -4071638817205573189L;

	private final HttpStatus status;
	
	public DataException(String mensaje, HttpStatus status) {
		super(mensaje);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}
	
}
