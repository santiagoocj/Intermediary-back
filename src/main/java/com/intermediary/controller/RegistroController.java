package com.intermediary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intermediary.dto.RegistroDTO;
import com.intermediary.dto.respuestas.RespuestaRegistroDTO;
import com.intermediary.service.impl.RegistroServiceImpl;

@Controller
@RequestMapping("/api")
public class RegistroController {
	
	@Autowired
	private RegistroServiceImpl registroServiceImpl;
	
	@PostMapping("/registro")
	public ResponseEntity<RespuestaRegistroDTO> realizarRegistro(@RequestBody RegistroDTO registroDTO){
		return registroServiceImpl.realizarRegistro(registroDTO);
	}

}
