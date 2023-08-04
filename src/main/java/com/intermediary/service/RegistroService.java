package com.intermediary.service;

import java.util.List;

import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;

import com.intermediary.dto.RegistroDTO;
import com.intermediary.dto.respuestas.RespuestaRegistroDTO;
import com.intermediary.entity.RegistroEntity;

public interface RegistroService {
	
	public RespuestaRegistroDTO realizarRegistro(RegistroDTO datosRegistro) throws Exception;

	public List<RegistroDTO> listarTodo() throws BindException;
	
	public RegistroEntity buscarXId(Long idRegistro);
	
	public void registrarDocumento(MultipartFile documento, Long idEmpresa);
	
	public void validarNitDuplicado(RegistroDTO datosRegistro);
	
}
