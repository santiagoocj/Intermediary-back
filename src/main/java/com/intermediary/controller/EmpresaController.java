package com.intermediary.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intermediary.catalogo.mensajes.CatalogoMensajesEmpresa;
import com.intermediary.catalogo.mensajes.CatalogoMensajesGenerales;
import com.intermediary.dto.EmpresaDTO;
import com.intermediary.dto.InfoBasicaUsuarioDTO;
import com.intermediary.dto.respuestas.RespuestaEmpresaDTO;
import com.intermediary.exception.BusinessExecption;
import com.intermediary.exception.DataException;
import com.intermediary.exception.util.ValidatorParameters;
import com.intermediary.service.impl.EmpresaServiceImpl;


@Controller
@RequestMapping("/api")
public class EmpresaController {
	
	private static Logger logger = LogManager.getLogger(EmpresaController.class);
	
	@Autowired
	private EmpresaServiceImpl empresaService;

	@GetMapping("/empresas")
	public ResponseEntity<?> listarTodo(){
		return empresaService.encontrarTodos();
	}
	
	@PostMapping("/empresas/registro/{id-solicitud-registro}")
	public ResponseEntity<RespuestaEmpresaDTO> registrarEmpresa(@PathVariable(name = "id-solicitud-registro") Long idSolicitudRegistro, @RequestBody InfoBasicaUsuarioDTO infoRegistroUsuario) throws BusinessExecption{
		ValidatorParameters.validarIdNulo(idSolicitudRegistro, CatalogoMensajesGenerales.URL_INCORRECTA);
		ValidatorParameters.validarNombreNulo(infoRegistroUsuario.getUserName(), CatalogoMensajesEmpresa.USER_NAME_NULO);
		ValidatorParameters.validarNombreNulo(infoRegistroUsuario.getPassword(), CatalogoMensajesEmpresa.CODIGO_NULO);
		try {
			RespuestaEmpresaDTO respuestaEmpresa = empresaService.registroEmpresa(idSolicitudRegistro, infoRegistroUsuario);
			logger.info("Registro empresa exitoso");
			return new ResponseEntity<>(respuestaEmpresa, HttpStatus.CREATED);
		} catch (BindException e) {
			throw new DataException(CatalogoMensajesEmpresa.ERROR_INSERTAR_EMPRESAS, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Secured({"ROLE_ADMINISTRADOR", "ROLE_EMPRESA_INICIAL", "ROLE_EMPRESA"})
	@PutMapping("/empresas/{id-empresa}")
	public ResponseEntity<RespuestaEmpresaDTO> editarInformacionEmpresa(@PathVariable(name = "id-empresa") Long idEmpresa, @RequestBody EmpresaDTO empresa){
		try {
			RespuestaEmpresaDTO respuestaEmpresa = empresaService.editarInformacion(idEmpresa, empresa);
			if(respuestaEmpresa.getMensaje().equals(CatalogoMensajesEmpresa.EMPRESA_NO_EXISTE)) {
				return new ResponseEntity<>(respuestaEmpresa, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(respuestaEmpresa, HttpStatus.OK);
		} catch (BindException e) {
			logger.error(() -> "Error editando información de la empresa "+ e.getMessage());
			throw new DataException(CatalogoMensajesEmpresa.ERROR_INSERTAR_EMPRESAS, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@Secured("ROLE_ADMINISTRADOR")
	@PostMapping("/empresas/{id-empresa}")
	public ResponseEntity<RespuestaEmpresaDTO> inactivarEmpresa(@PathVariable(name = "id-empresa") Long idEmpresa){
		return new ResponseEntity<>(empresaService.inactivar(idEmpresa), HttpStatus.OK);
	}
}
