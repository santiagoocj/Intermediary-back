package com.intermediary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intermediary.catalogo.mensajes.CatalogoMensajesEmpresa;
import com.intermediary.dto.EmpresaDTO;
import com.intermediary.dto.respuestas.RespuestaEmpresaDTO;
import com.intermediary.exception.BusinessExecption;
import com.intermediary.exception.util.ValidatorParameters;
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
	
	@PostMapping("/empresas/{idRepresentante}/{idMembresia}/{idRegistro}")
	public ResponseEntity<RespuestaEmpresaDTO> registrarEmpresa(@PathVariable Long idRepresentante, @PathVariable Long idMembresia, @PathVariable Long idRegistro) throws BusinessExecption{
		ValidatorParameters.validarIdNulo(idRepresentante, CatalogoMensajesEmpresa.REPRESENTANTE_REQUERIDO);
		ValidatorParameters.validarIdNulo(idMembresia, CatalogoMensajesEmpresa.MEMBRESIA_REQUERIDA);
		return empresaService.registroEmpresa(idRepresentante, idMembresia, idRegistro);
	}
	
	@PutMapping("/empresas/{idEmpresa}")
	public ResponseEntity<RespuestaEmpresaDTO> editarInformacionEmpresa(@PathVariable Long idEmpresa, @RequestBody EmpresaDTO empresa){
		return empresaService.editarInformacion(idEmpresa, empresa);
	}
	
	@PostMapping("/empresas/{idMembresia}/{idEmpresa}")
	public ResponseEntity<RespuestaEmpresaDTO> renovarMembresia(@PathVariable Long idMembresia, @PathVariable Long idEmpresa){
		return empresaService.renovarMembresia(idEmpresa, idMembresia);
	}
	
	@PostMapping("/empresas/{idEmpresa}")
	public ResponseEntity<RespuestaEmpresaDTO> inactivarEmpresa(@PathVariable Long idEmpresa){
		return empresaService.inactivar(idEmpresa);
	}
}
