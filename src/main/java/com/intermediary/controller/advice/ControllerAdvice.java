package com.intermediary.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.intermediary.dto.ErrorDTO;
import com.intermediary.exception.BusinessExecption;
import com.intermediary.exception.DataException;
import com.intermediary.exception.GenericExecption;


@RestControllerAdvice
public class ControllerAdvice {
	
	@ExceptionHandler(value = DataException.class)
	public ResponseEntity<ErrorDTO> requestExceptionHandler(DataException ex){
		ErrorDTO error = ErrorDTO.builder().error(ex.getMessage()).build();
		return new ResponseEntity<>(error, ex.getStatus());
	}
	
	@ExceptionHandler(value = BusinessExecption.class)
	public ResponseEntity<ErrorDTO> requestExceptionBusiness(BusinessExecption ex){
		ErrorDTO error = ErrorDTO.builder().error(ex.getMessage()).build();
		return new ResponseEntity<>(error, ex.getStatus());
	}
	
	@ExceptionHandler(value = GenericExecption.class)
	public ResponseEntity<ErrorDTO> requestGenericException(GenericExecption ex){
		ErrorDTO error = ErrorDTO.builder().error(ex.getMessage()).build();
		return new ResponseEntity<>(error, ex.getStatus());
	}

}
