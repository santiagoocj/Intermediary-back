package com.intermediary.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intermediary.catalogo.mensajes.CatalogoMensajesEmpresaEntity;
import com.intermediary.dto.parametros.RegistroEmpresaDTO;
import com.intermediary.entity.EmpresaEntity;
import com.intermediary.service.impl.EmpresaServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api")
@Slf4j
public class EmpresaController {
	
	@Autowired
	private EmpresaServiceImpl empresaService;

	@GetMapping("/empresas")
	public ResponseEntity<?> listarTodo(){
		return empresaService.encontrarTodos();
	}
	
	@PostMapping("/empresas")
	public ResponseEntity<?> registrarEmpresa(@Valid @RequestBody RegistroEmpresaDTO datosRegistroEmpresa, BindingResult validacionesEntidad){
		log.info("ERRORES VALIDACIONES =========== " + validacionesEntidad.hasErrors());
		if(validacionesEntidad.hasErrors()) {
			log.info("hay ERRORES =========================");
			return listadoErroresValidacionEntidad(validacionesEntidad);
		}
		EmpresaEntity empresaRegistro = datosRegistroEmpresa.getEmpresa();
		empresaService.registroEmpresa(empresaRegistro);
		return new ResponseEntity<String>("¡Se a registrado la empresa!", HttpStatus.OK);
	}
	
	private ResponseEntity<Map<String, Object>> listadoErroresValidacionEntidad(BindingResult validacionesEntidad){
		Map<String, Object> respuesta = new HashMap<>();
		List<String> ValidacionesRegistro = validacionesEntidad.getFieldErrors()
				.stream()
				.map(errores -> {
					return "El campo '" + errores.getField() + "' " + errores.getDefaultMessage();
				}).collect(Collectors.toList());
		respuesta.put(CatalogoMensajesEmpresaEntity.ERRORES, ValidacionesRegistro);
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
	}
	
}
