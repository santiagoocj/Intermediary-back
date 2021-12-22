package com.intermediary.service;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;

import com.intermediary.dto.EmpresaDTO;


public interface EmpresaService {

	public ResponseEntity<?> encontrarTodos() throws BindException;
	
	public ResponseEntity<?> registroEmpresa(EmpresaDTO empresaRegistro);
}
