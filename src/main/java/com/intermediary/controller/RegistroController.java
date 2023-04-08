package com.intermediary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intermediary.catalogo.mensajes.CatalogoMensajesRegistro;
import com.intermediary.dto.RegistroDTO;
import com.intermediary.dto.respuestas.RespuestaRegistroDTO;
import com.intermediary.exception.BusinessExecption;
import com.intermediary.exception.util.ValidatorParameters;
import com.intermediary.service.impl.RegistroServiceImpl;

@RestController
@RequestMapping("/api")
public class RegistroController {
	
	@Autowired
	private RegistroServiceImpl registroServiceImpl;
	
	@PostMapping("/registro")
	public ResponseEntity<RespuestaRegistroDTO> realizarRegistro(@RequestBody RegistroDTO empresa) throws BusinessExecption{
		ValidatorParameters.validarNitNulo(empresa.getNit(), CatalogoMensajesRegistro.NIT_REQUERIDO);
		ValidatorParameters.validarNombreNulo(empresa.getNombreEmpresa(), CatalogoMensajesRegistro.NOMBRE_REQUERIDO);
		ValidatorParameters.validarRazonSocialNulo(empresa.getRazonSocial(), CatalogoMensajesRegistro.RAZON_SOCIAL_REQUERIDO);
		ValidatorParameters.validarCodigoCiuNulo(empresa.getCodigoCiu(), CatalogoMensajesRegistro.CODIGO_CIU_REQUERIDO);
		ValidatorParameters.validarActividadPrincipalNulo(empresa.getActividadPrincipal(), CatalogoMensajesRegistro.ACTIVIDAD_PRINCIPAL_REQUERIDO);
		ValidatorParameters.validarTipoPersonaNulo(empresa.getTipoPersona(), CatalogoMensajesRegistro.TIPO_PERSONA_REQUERIDO);
		ValidatorParameters.validarCelularNulo(empresa.getCelular(), CatalogoMensajesRegistro.CELULAR_REQUERIDO);
		ValidatorParameters.validarEmailNulo(empresa.getEmail(), CatalogoMensajesRegistro.EMAIL_REQUERIDO);
		ValidatorParameters.validarEmailPermitido(empresa.getEmail(), CatalogoMensajesRegistro.EMAIL_NO_VALIDO);
		return registroServiceImpl.realizarRegistro(empresa);
	}
	
	@Secured("ROLE_ADMINISTRADOR")
	@GetMapping("/registro")
	public ResponseEntity<List<RegistroDTO>> listarRegistro(){
		return registroServiceImpl.listarTodo();
	}

}
