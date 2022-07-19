package com.intermediary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intermediary.catalogo.mensajes.CatalogoMensajesRepresentanteLegal;
import com.intermediary.dto.RepresentanteLegalDTO;
import com.intermediary.exception.BusinessExecption;
import com.intermediary.exception.util.ValidatorParameters;
import com.intermediary.service.impl.RepresentanteLegalServiceImpl;

@Controller
@RequestMapping("/api")
public class RepresentanteLegalController {
	
	@Autowired
	private RepresentanteLegalServiceImpl representantelegalService;

	@PostMapping("/representantelegal")
	public ResponseEntity<?> registroRepresentanteLeal(@RequestBody RepresentanteLegalDTO datosRepresentanteLegal) throws BusinessExecption{
		ValidatorParameters.validarDocumentoNulo(datosRepresentanteLegal.getTipoDocumento(), CatalogoMensajesRepresentanteLegal.TIPO_DOCUMENTO_REQUERIDO);
		ValidatorParameters.validarDocumentoNulo(datosRepresentanteLegal.getDocumento(), CatalogoMensajesRepresentanteLegal.NUMERO_DOCUMENTO_REQUERIDO);
		ValidatorParameters.validarNombreNulo(datosRepresentanteLegal.getNombre(), CatalogoMensajesRepresentanteLegal.NOMBRE_REQUERIDO);
		ValidatorParameters.validarCelularNulo(datosRepresentanteLegal.getCelular(), CatalogoMensajesRepresentanteLegal.CELULAR_REQUERIDO);
		ValidatorParameters.validarEmailNulo(datosRepresentanteLegal.getEmail(), CatalogoMensajesRepresentanteLegal.CORREO_REQUERIDO);
		ValidatorParameters.validarEmailPermitido(datosRepresentanteLegal.getEmail(), CatalogoMensajesRepresentanteLegal.FORMATO_EMAIL_NO_VALIDO);
		return representantelegalService.registrarRepresentantelegal(datosRepresentanteLegal);
	}
	
	@Secured("ROLE_ADMINISTRADOR")
	@GetMapping("/representantelegal/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id){
		return representantelegalService.buscar(id);
	}

}
