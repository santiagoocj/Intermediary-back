package com.intermediary.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.intermediary.dto.RegistroDTO;
import com.intermediary.dto.respuestas.RespuestaRegistroDTO;
import com.intermediary.service.RegistroService;

@Service("RegistroService")
public class RegistroServiceImpl implements RegistroService{

	@Override
	public ResponseEntity<RespuestaRegistroDTO> realizarRegistro(RegistroDTO datosRegistro) {
		return null;
	}

}
