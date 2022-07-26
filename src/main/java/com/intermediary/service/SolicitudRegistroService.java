package com.intermediary.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;

import com.intermediary.dto.CambiarEstadoSolicitudRegistroDTO;
import com.intermediary.dto.respuestas.ListarSolicitudRegistroDTO;
import com.intermediary.dto.respuestas.RespuestaEstadoSolicitudRegistroDTO;
import com.intermediary.entity.SolicitudRegistroEntity;

public interface SolicitudRegistroService {
	
	public ResponseEntity<ListarSolicitudRegistroDTO> listarTodo();
	
	public ResponseEntity<RespuestaEstadoSolicitudRegistroDTO> modificar(Long id, CambiarEstadoSolicitudRegistroDTO solicitudCambiar);
	
	public boolean existeSolicitud(Long id);
	
	public ResponseEntity<Map<String, String>> crearSolicitud(Long idEmpresa, Long idRepresentante) throws BindException;
	
	public SolicitudRegistroEntity findById(Long idSolicitudRegistro);
	
	public void validarEstadoSolicitud(SolicitudRegistroEntity solicitudRegistro);
}
