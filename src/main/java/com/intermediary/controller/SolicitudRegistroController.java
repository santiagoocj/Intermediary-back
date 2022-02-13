package com.intermediary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intermediary.dto.respuestas.ListarSolicitudRegistroDTO;
import com.intermediary.service.impl.SolicitudRegistroServiceImpl;

@Controller
@RequestMapping("/api")
public class SolicitudRegistroController {
	
	@Autowired
	private SolicitudRegistroServiceImpl solicitudRegistroServiceImpl;
	
	@GetMapping("/solicitud-registro")
	public ResponseEntity<ListarSolicitudRegistroDTO> listarTodo(){
		return solicitudRegistroServiceImpl.listarTodo();
	}

}
