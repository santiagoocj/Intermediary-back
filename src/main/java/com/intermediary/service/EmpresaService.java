package com.intermediary.service;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;

import com.intermediary.dto.parametros.RegistroEmpresaDTO;


public interface EmpresaService {

	public ResponseEntity<?> encontrarTodos() throws BindException;
	
	public ResponseEntity<?> registroEmpresa(RegistroEmpresaDTO empresaRegistro);
}
