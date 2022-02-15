package com.intermediary.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;

import com.intermediary.dto.CambiarEstadoSolicitudRegistroDTO;
import com.intermediary.dto.respuestas.ListarSolicitudRegistroDTO;
import com.intermediary.dto.respuestas.RespuestaEstadoSolicitudRegistroDTO;
import com.intermediary.entity.RegistroEntity;

public interface SolicitudRegistroService {
	
	public void guardarSolicitud(RegistroEntity registro) throws BindException;
	
	public ResponseEntity<ListarSolicitudRegistroDTO> listarTodo();
	
	public ResponseEntity<RespuestaEstadoSolicitudRegistroDTO> modificar(Long id, CambiarEstadoSolicitudRegistroDTO solicitudCambiar);
	
	public boolean existeSolicitud(Long id);
}
