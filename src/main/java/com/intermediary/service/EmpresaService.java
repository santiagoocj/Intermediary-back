package com.intermediary.service;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;

import com.intermediary.dto.EmpresaDTO;
import com.intermediary.dto.InfoBasicaUsuarioDTO;
import com.intermediary.dto.respuestas.RespuestaEmpresaDTO;


public interface EmpresaService {

	public ResponseEntity<?> encontrarTodos() throws BindException;
	
	public ResponseEntity<RespuestaEmpresaDTO> registroEmpresa(Long idSolicitudRegistro, InfoBasicaUsuarioDTO infoBasicaUsuario);
	
	public ResponseEntity<RespuestaEmpresaDTO> editarInformacion(Long idEmpresa, EmpresaDTO empresaInformacionNueva);
	
	public ResponseEntity<RespuestaEmpresaDTO> renovarMembresia(Long idEmpresa, Long idMembresia);
	
	public ResponseEntity<RespuestaEmpresaDTO> inactivar(Long idEmpresa);
	
}
