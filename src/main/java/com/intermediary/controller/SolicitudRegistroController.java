package com.intermediary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intermediary.catalogo.mensajes.CatalogoMensajesGenerales;
import com.intermediary.catalogo.mensajes.CatalogoMensajesSolicitudRegistro;
import com.intermediary.dto.CambiarEstadoSolicitudRegistroDTO;
import com.intermediary.dto.respuestas.ListarSolicitudRegistroDTO;
import com.intermediary.dto.respuestas.RespuestaEstadoSolicitudRegistroDTO;
import com.intermediary.exception.BusinessExecption;
import com.intermediary.exception.DataException;
import com.intermediary.exception.util.ValidatorParameters;
import com.intermediary.service.impl.SolicitudRegistroServiceImpl;

@Controller
@RequestMapping("/api")
public class SolicitudRegistroController {
	
	@Autowired
	private SolicitudRegistroServiceImpl solicitudRegistroServiceImpl;
	
	@GetMapping("/solicitud-registro")
	public ResponseEntity<ListarSolicitudRegistroDTO> listarTodo(){
		return solicitudRegistroServiceImpl.listarTodo();
	}
	
	@PostMapping("/solicitud-registro/{id}")
	public ResponseEntity<RespuestaEstadoSolicitudRegistroDTO> cambiarEstado(@RequestBody CambiarEstadoSolicitudRegistroDTO solicitudRegistro, @PathVariable Long id) throws BusinessExecption{
		if(!solicitudRegistroServiceImpl.existeSolicitud(id)) {
			throw new DataException(CatalogoMensajesSolicitudRegistro.SOLICITUD_NO_ENCONTRADA, HttpStatus.NOT_FOUND);
		}
		ValidatorParameters.validarContenidoCorreoNulo(solicitudRegistro.getContenidoCorreoEstadoSolicitud(), CatalogoMensajesGenerales.CONTENIDO_CORREO_NULO);
		ValidatorParameters.validarContenidoCorreoVacio(solicitudRegistro.getContenidoCorreoEstadoSolicitud(), CatalogoMensajesGenerales.CONTENIDO_CORREO_VACIO);
		return solicitudRegistroServiceImpl.modificar(id,solicitudRegistro);
	}

}
