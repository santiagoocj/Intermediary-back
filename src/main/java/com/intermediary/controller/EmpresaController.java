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
import com.intermediary.entity.EmpresaEntity;
import com.intermediary.service.impl.EmpresaServiceImpl;

@Controller
@RequestMapping("/api")
public class EmpresaController {
	
	@Autowired
	private EmpresaServiceImpl empresaService;

	@GetMapping("/empresas")
	public ResponseEntity<?> listarTodo(){
		return empresaService.encontrarTodos();
	}
	
	@PostMapping("/empresas/{representante}/{categoria}")
	public ResponseEntity<?> registrarEmpresa(@Valid @RequestBody EmpresaEntity empresa, BindingResult validacionesEntidad, @PathVariable Long representante, @PathVariable Long categoria){
		if(validacionesEntidad.hasErrors()) {
			listadoErroresValidacionEntidad(validacionesEntidad);
		}
		EmpresaEntity empresaRegistro = empresa;
		return empresaService.registroEmpresa(empresaRegistro);
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
