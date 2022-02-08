package com.intermediary.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.intermediary.catalogo.mensajes.CatalogoMensajesRegistro;
import com.intermediary.dto.RegistroDTO;
import com.intermediary.dto.respuestas.RespuestaRegistroDTO;
import com.intermediary.entity.RegistroEntity;
import com.intermediary.exception.DataException;
import com.intermediary.repository.RegistroRepository;
import com.intermediary.service.RegistroService;
import com.intermediary.utils.converter.RegistroConverter;

@Service("RegistroService")
public class RegistroServiceImpl implements RegistroService{
	
	@Autowired
	@Qualifier("RegistroRepository")
	private RegistroRepository registroRepository;
	
	@Autowired
	private SolicitudRegistroServiceImpl solicitudRegistroServiceImpl;
	
	@Autowired
	private RegistroConverter registroConverter;

	@Override
	public ResponseEntity<RespuestaRegistroDTO> realizarRegistro(RegistroDTO datosRegistro) {
		RespuestaRegistroDTO respuesta = null;
		RegistroEntity registroEntity = null;
		try {
			registroEntity = registroConverter.ModelToEntity(datosRegistro);
			registroRepository.save(registroEntity);
			solicitudRegistroServiceImpl.guardarSolicitud(registroEntity);
			respuesta = RespuestaRegistroDTO.builder().registro(datosRegistro).mensaje(CatalogoMensajesRegistro.REGISTRO_EXITOSO).build();
		}catch (Exception e) {
			throw new DataException(CatalogoMensajesRegistro.REGISTRO_FALLIDO, HttpStatus.BAD_REQUEST);
		} 
		return new ResponseEntity<RespuestaRegistroDTO>(respuesta, HttpStatus.CREATED);
	}

}
