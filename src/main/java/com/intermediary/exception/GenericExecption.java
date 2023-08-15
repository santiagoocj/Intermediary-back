package com.intermediary.exception;

import org.springframework.http.HttpStatus;

public class GenericExecption extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8211551935232293149L;
	
	private final HttpStatus status;
	
	public GenericExecption(String mensaje, HttpStatus status) {
		super(mensaje);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}
}
