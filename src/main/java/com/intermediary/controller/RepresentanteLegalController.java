package com.intermediary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intermediary.catalogo.mensajes.CatalogoMensajesRepresentanteLegal;
import com.intermediary.dto.RepresentanteLegalDTO;
import com.intermediary.exception.DataException;
import com.intermediary.service.impl.RepresentanteLegalServiceImpl;


@Controller
@RequestMapping("/api")
public class RepresentanteLegalController {
	
	@Autowired
	private RepresentanteLegalServiceImpl representantelegalService;

	@PostMapping("/representantelegal")
	public ResponseEntity<?> registroRepresentanteLeal(@RequestBody RepresentanteLegalDTO datosRepresentanteLegal){
		validacionDatosRepresentanteLegal(datosRepresentanteLegal);
		return representantelegalService.registrarRepresentantelegal(datosRepresentanteLegal);
	}
	
	@GetMapping("/representantelegal/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id){
		return representantelegalService.buscar(id);
	}
	
	
	private void validacionDatosRepresentanteLegal(RepresentanteLegalDTO representanteLegalDTO) {
		if(representanteLegalDTO.getTipoDocumento() == null) {
			throw new DataException(CatalogoMensajesRepresentanteLegal.TIPO_DOCUMENTO_REQUERIDO, HttpStatus.BAD_REQUEST);
		}
		if(representanteLegalDTO.getDocumento() == null) {
			throw new DataException(CatalogoMensajesRepresentanteLegal.NUMERO_DOCUMENTO_REQUERIDO, HttpStatus.BAD_REQUEST);
		}
		if(representanteLegalDTO.getNombre() == null) {
			throw new DataException(CatalogoMensajesRepresentanteLegal.NOMBRE_REQUERIDO, HttpStatus.BAD_REQUEST);
		}
		if(representanteLegalDTO.getCelular() == null) {
			throw new DataException(CatalogoMensajesRepresentanteLegal.CELULAR_REQUERIDO, HttpStatus.BAD_REQUEST);
		}
		if(representanteLegalDTO.getEmail() == null) {
			throw new DataException(CatalogoMensajesRepresentanteLegal.CORREO_REQUERIDO, HttpStatus.BAD_REQUEST);
		}
	}
}
