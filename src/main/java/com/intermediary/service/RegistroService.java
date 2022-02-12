package com.intermediary.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.intermediary.dto.RegistroDTO;
import com.intermediary.dto.respuestas.RespuestaRegistroDTO;
import com.intermediary.exception.BusinessExecption;

public interface RegistroService {
	
	public ResponseEntity<RespuestaRegistroDTO> realizarRegistro(RegistroDTO datosRegistro);

	public ResponseEntity<List<RegistroDTO>> listarTodo();
}
