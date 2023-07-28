package com.intermediary.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.intermediary.dto.RegistroDTO;
import com.intermediary.dto.respuestas.RespuestaRegistroDTO;
import com.intermediary.entity.RegistroEntity;

public interface RegistroService {
	
	public ResponseEntity<RespuestaRegistroDTO> realizarRegistro(RegistroDTO datosRegistro);

	public ResponseEntity<List<RegistroDTO>> listarTodo();
	
	public RegistroEntity buscarXId(Long idRegistro);
	
	public void registrarDocumento(MultipartFile documento, Long idEmpresa);
	
	public void validarNitDuplicado(RegistroDTO datosRegistro);
	
}
