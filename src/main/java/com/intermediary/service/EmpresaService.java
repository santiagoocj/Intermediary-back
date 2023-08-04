package com.intermediary.service;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;

import com.intermediary.dto.EmpresaDTO;
import com.intermediary.dto.InfoBasicaUsuarioDTO;
import com.intermediary.dto.respuestas.RespuestaEmpresaDTO;
import com.intermediary.entity.EmpresaEntity;


public interface EmpresaService {

	public ResponseEntity<?> encontrarTodos() throws BindException;
	
	public RespuestaEmpresaDTO registroEmpresa(Long idSolicitudRegistro, InfoBasicaUsuarioDTO infoBasicaUsuario) throws BindException;
	
	public RespuestaEmpresaDTO editarInformacion(Long idEmpresa, EmpresaDTO empresaInformacionNueva) throws BindException;
	
	public RespuestaEmpresaDTO inactivar(Long idEmpresa);
	
	public EmpresaEntity buscarXId(Long idEmpresa);
	
	public EmpresaEntity actualizarEmpresa(EmpresaEntity empresa);
	
}
