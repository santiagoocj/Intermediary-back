package com.intermediary.service;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;


public interface EmpresaService {

	public ResponseEntity<?> encontrarTodos() throws BindException;
}
