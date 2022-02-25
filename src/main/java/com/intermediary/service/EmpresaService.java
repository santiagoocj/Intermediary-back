package com.intermediary.service;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;

import com.intermediary.dto.respuestas.RespuestaEmpresaDTO;


public interface EmpresaService {

	public ResponseEntity<?> encontrarTodos() throws BindException;
	
	public ResponseEntity<RespuestaEmpresaDTO> registroEmpresa(Long idRepresentante, Long idMembresia, Long idRegistro);
}
