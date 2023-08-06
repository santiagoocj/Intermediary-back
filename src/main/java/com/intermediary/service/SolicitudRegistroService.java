package com.intermediary.service;

import java.util.Map;

import org.springframework.validation.BindException;

import com.intermediary.dto.CambiarEstadoSolicitudRegistroDTO;
import com.intermediary.dto.respuestas.ListarSolicitudRegistroDTO;
import com.intermediary.dto.respuestas.RespuestaEstadoSolicitudRegistroDTO;
import com.intermediary.entity.SolicitudRegistroEntity;

public interface SolicitudRegistroService {
	
	public ListarSolicitudRegistroDTO listarTodo() throws BindException;
	
	public RespuestaEstadoSolicitudRegistroDTO modificar(Long id, CambiarEstadoSolicitudRegistroDTO solicitudCambiar) throws BindException;
	
	public boolean existeSolicitud(Long id);
	
	public Map<String, String> crearSolicitud(Long idEmpresa, Long idRepresentante) throws BindException;
	
	public SolicitudRegistroEntity findById(Long idSolicitudRegistro);
	
	public void validarEstadoSolicitud(SolicitudRegistroEntity solicitudRegistro);
}
