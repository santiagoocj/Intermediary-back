package com.intermediary.controller;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.intermediary.catalogo.mensajes.CatalogoMensajesGenerales;
import com.intermediary.catalogo.mensajes.CatalogoMensajesSolicitudRegistro;
import com.intermediary.dto.CambiarEstadoSolicitudRegistroDTO;
import com.intermediary.dto.respuestas.ListarSolicitudRegistroDTO;
import com.intermediary.dto.respuestas.RespuestaEstadoSolicitudRegistroDTO;
import com.intermediary.exception.BusinessExecption;
import com.intermediary.exception.DataException;
import com.intermediary.exception.util.ValidatorParameters;
import com.intermediary.service.impl.RegistroServiceImpl;
import com.intermediary.service.impl.SolicitudRegistroServiceImpl;

@RestController
@RequestMapping("/api")
public class SolicitudRegistroController {
	
	private static Logger logger = LogManager.getLogger(SolicitudRegistroController.class);
	
	@Autowired
	private SolicitudRegistroServiceImpl solicitudRegistroServiceImpl;
	
	@Autowired
	private RegistroServiceImpl registroServiceImpl;
	
	@Secured("ROLE_ADMINISTRADOR")
	@GetMapping("/solicitud-registro")
	public ResponseEntity<ListarSolicitudRegistroDTO> listarTodo(){
		try {
			return new ResponseEntity<ListarSolicitudRegistroDTO>(solicitudRegistroServiceImpl.listarTodo(), HttpStatus.OK);
		} catch (BindException e) {
			logger.error("Error listando las solicitudes de registro " + e.getMessage());
			throw new DataException(CatalogoMensajesSolicitudRegistro.ERROR_LISTAR_SOLICITUDES_REGISTRO, HttpStatus.BAD_REQUEST);
		}
	}
	
	@Secured("ROLE_ADMINISTRADOR")
	@PostMapping("/solicitud-registro/{id}")
	public ResponseEntity<RespuestaEstadoSolicitudRegistroDTO> cambiarEstado(@RequestBody CambiarEstadoSolicitudRegistroDTO solicitudRegistro, @PathVariable Long id) throws BusinessExecption{
		if(!solicitudRegistroServiceImpl.existeSolicitud(id)) {
			logger.info("Solicitud con id " + id + " no encontrada");
			throw new DataException(CatalogoMensajesSolicitudRegistro.SOLICITUD_NO_ENCONTRADA, HttpStatus.NOT_FOUND);
		}
		ValidatorParameters.validarContenidoCorreoNulo(solicitudRegistro.getContenidoCorreoEstadoSolicitud(), CatalogoMensajesGenerales.CONTENIDO_CORREO_NULO);
		ValidatorParameters.validarContenidoCorreoVacio(solicitudRegistro.getContenidoCorreoEstadoSolicitud(), CatalogoMensajesGenerales.CONTENIDO_CORREO_VACIO);
		try {
			return new ResponseEntity<RespuestaEstadoSolicitudRegistroDTO>(solicitudRegistroServiceImpl.modificar(id,solicitudRegistro), HttpStatus.OK);
		} catch (BindException e) {
			throw new DataException(CatalogoMensajesSolicitudRegistro.ERROR_EDITAR_SOLICITUD_DE_REGISTRO, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/solicitud-registro/{id-empresa}/{id-representante}")
	public ResponseEntity<Map<String, String>> crearSolicitud(@PathVariable(name = "id-empresa") long idEmpresa, @PathVariable(name = "id-representante") long idRepresentante) throws BusinessExecption, BindException{
		ValidatorParameters.validarIdNulo(idEmpresa, CatalogoMensajesGenerales.ID_NULO);
		ValidatorParameters.validarIdNulo(idRepresentante, CatalogoMensajesGenerales.ID_NULO);
		logger.info("Solicitud de registro iniciada, paramatros requeridos son válidos");
		return new ResponseEntity<>(solicitudRegistroServiceImpl.crearSolicitud(idEmpresa, idRepresentante), HttpStatus.OK);
		
	}
	
	@PostMapping("/solicitud-registro/registro/documento")
	public void realizarRegistroDocumento(@RequestParam("documento") MultipartFile documento, @RequestParam("empresa") Long idEmpresa) {
		registroServiceImpl.registrarDocumento(documento, idEmpresa);
	}

}
