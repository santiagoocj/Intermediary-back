package com.intermediary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intermediary.catalogo.mensajes.CatalogoMensajesEmpresa;
import com.intermediary.dto.EmpresaDTO;
import com.intermediary.exception.DataException;
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
	
	@PostMapping("/empresas")
	public ResponseEntity<?> registrarEmpresa(@RequestBody EmpresaDTO datosRegistroEmpresa){
		erroresDatosRegistroEmpresa(datosRegistroEmpresa);
		return empresaService.registroEmpresa(datosRegistroEmpresa);
	
	}
	
	private void erroresDatosRegistroEmpresa(EmpresaDTO datosRegistroEmpresa) {
		if(datosRegistroEmpresa.getNit() == null) {
			throw new DataException(CatalogoMensajesEmpresa.NIT_REQUERIDO, HttpStatus.BAD_REQUEST);
		}
		if(datosRegistroEmpresa.getCorreo() == null) {
			throw new DataException(CatalogoMensajesEmpresa.CORREO_REQUERIDO, HttpStatus.BAD_REQUEST);
		}
	}

}
