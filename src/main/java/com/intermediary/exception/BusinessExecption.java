package com.intermediary.exception;

import org.springframework.http.HttpStatus;

public class BusinessExecption extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2202436054232940143L;
	
	private final HttpStatus status;

	public BusinessExecption(String mensaje, HttpStatus status) {
		super(mensaje);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

}
