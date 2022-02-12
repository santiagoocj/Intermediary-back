package com.intermediary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intermediary.catalogo.mensajes.CatalogoMensajesRegistro;
import com.intermediary.dto.RegistroDTO;
import com.intermediary.dto.respuestas.RespuestaRegistroDTO;
import com.intermediary.exception.BusinessExecption;
import com.intermediary.exception.util.Validator;
import com.intermediary.service.impl.RegistroServiceImpl;

@Controller
@RequestMapping("/api")
public class RegistroController {
	
	@Autowired
	private RegistroServiceImpl registroServiceImpl;
	
	@PostMapping("/registro")
	public ResponseEntity<RespuestaRegistroDTO> realizarRegistro(@RequestBody RegistroDTO registroDTO) throws BusinessExecption{
		Validator.validarNitNulo(registroDTO.getNit(), CatalogoMensajesRegistro.NIT_REQUERIDO);
		Validator.validarNombreNulo(registroDTO.getNombreEmpresa(), CatalogoMensajesRegistro.NOMBRE_REQUERIDO);
		Validator.validarRazonSocialNulo(registroDTO.getRazonSocial(), CatalogoMensajesRegistro.RAZON_SOCIAL_REQUERIDO);
		Validator.validarCodigoCiuNulo(registroDTO.getCodigoCiu(), CatalogoMensajesRegistro.CODIGO_CIU_REQUERIDO);
		Validator.validarActividadPrincipalNulo(registroDTO.getActividadPrincipal(), CatalogoMensajesRegistro.ACTIVIDAD_PRINCIPAL_REQUERIDO);
		Validator.validarTipoPersonaNulo(registroDTO.getTipoPersona(), CatalogoMensajesRegistro.TIPO_PERSONA_REQUERIDO);
		Validator.validarCelularNulo(registroDTO.getCelular(), CatalogoMensajesRegistro.CELULAR_REQUERIDO);
		Validator.validarEmailNulo(registroDTO.getEmail(), CatalogoMensajesRegistro.EMAIL_REQUERIDO);
		Validator.validarEmailPermitido(registroDTO.getEmail(), CatalogoMensajesRegistro.EMAIL_NO_VALIDO);
		return registroServiceImpl.realizarRegistro(registroDTO);
	}
	
	@GetMapping("/registro")
	public ResponseEntity<List<RegistroDTO>> listarRegistro(){
		return registroServiceImpl.listarTodo();
	}

}
