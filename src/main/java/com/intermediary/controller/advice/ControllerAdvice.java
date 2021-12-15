package com.intermediary.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.intermediary.dto.ErrorDTO;
import com.intermediary.exception.DataException;


@RestControllerAdvice
public class ControllerAdvice {
	
	@ExceptionHandler(value = DataException.class)
	public ResponseEntity<ErrorDTO> requestExceptionHandler(DataException ex){
		ErrorDTO error = ErrorDTO.builder().error(ex.getMessage()).build();
		return new ResponseEntity<ErrorDTO>(error, ex.getStatus());
	}

}
