package com.intermediary.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intermediary.catalogo.mensajes.CatalogoMensajesRegistro;
import com.intermediary.dto.RegistroDTO;
import com.intermediary.dto.respuestas.RespuestaRegistroDTO;
import com.intermediary.exception.BusinessExecption;
import com.intermediary.exception.DataException;
import com.intermediary.exception.util.ValidatorParameters;
import com.intermediary.service.impl.RegistroServiceImpl;

@RestController
@RequestMapping("/api")
public class RegistroController {
	
	private static Logger logger = LogManager.getLogger(RegistroController.class);
	
	@Autowired
	private RegistroServiceImpl registroServiceImpl;
	
	@PostMapping("/registro")
	public ResponseEntity<RespuestaRegistroDTO> realizarRegistro(@RequestBody RegistroDTO empresa) throws BusinessExecption{
		validarParametrosRegistre(empresa);
		logger.info("Registro inicial, parámetros requeridos pasaron");
		try {
			RespuestaRegistroDTO respuestaRegistroDTO = registroServiceImpl.realizarRegistro(empresa);
			return new ResponseEntity<>(respuestaRegistroDTO, HttpStatus.CREATED);
		} catch (BindException e) {
			logger.error(() -> "Error realizar registro: " + e.getMessage());
			throw new DataException(CatalogoMensajesRegistro.REGISTRO_FALLIDO, HttpStatus.BAD_REQUEST);
		}
	}
	
	@Secured("ROLE_ADMINISTRADOR")
	@GetMapping("/registro")
	public ResponseEntity<List<RegistroDTO>> listarRegistro(){
		try {
			List<RegistroDTO> registros = registroServiceImpl.listarTodo();
			return new ResponseEntity<>(registros, HttpStatus.OK);
		} catch (BindException e) {
			logger.error(() -> "Error listando registros. Error " + e.getMessage());
			throw new DataException(CatalogoMensajesRegistro.ERROR_LISTAR_REGISTRO, HttpStatus.BAD_REQUEST);
		}
	}
	
	private void validarParametrosRegistre(RegistroDTO empresa) throws BusinessExecption {
		ValidatorParameters.validarNitNulo(empresa.getNit(), CatalogoMensajesRegistro.NIT_REQUERIDO);
		ValidatorParameters.validarFormatoNit(empresa.getNit(), CatalogoMensajesRegistro.FORMATO_NIT_INVALIDO);
		ValidatorParameters.validarTamanoMinimoNit(empresa.getNit(), CatalogoMensajesRegistro.TAMANO_MINIMO_NIT_INVALIDO);
		ValidatorParameters.validarTamanoNit(empresa.getNit(), CatalogoMensajesRegistro.TAMANO_NIT_INVALIDO);
		ValidatorParameters.validarNombreNulo(empresa.getNombreEmpresa(), CatalogoMensajesRegistro.NOMBRE_REQUERIDO);
		ValidatorParameters.validartamanoMaximoNombre(empresa.getNombreEmpresa(), CatalogoMensajesRegistro.TAMANO_NOMBRE_EMPRESA_INVALIDO);
		ValidatorParameters.validarRazonSocialNulo(empresa.getRazonSocial(), CatalogoMensajesRegistro.RAZON_SOCIAL_REQUERIDO);
		ValidatorParameters.validarCodigoCiuNulo(empresa.getCodigoCiu(), CatalogoMensajesRegistro.CODIGO_CIU_REQUERIDO);
		ValidatorParameters.validarActividadPrincipalNulo(empresa.getActividadPrincipal(), CatalogoMensajesRegistro.ACTIVIDAD_PRINCIPAL_REQUERIDO);
		ValidatorParameters.validarTipoPersonaNulo(empresa.getTipoPersona(), CatalogoMensajesRegistro.TIPO_PERSONA_REQUERIDO);
		ValidatorParameters.validarCelularNulo(empresa.getCelular(), CatalogoMensajesRegistro.CELULAR_REQUERIDO);
		ValidatorParameters.validarEmailNulo(empresa.getEmail(), CatalogoMensajesRegistro.EMAIL_REQUERIDO);
		ValidatorParameters.validarEmailPermitido(empresa.getEmail(), CatalogoMensajesRegistro.EMAIL_NO_VALIDO);
	}

}
