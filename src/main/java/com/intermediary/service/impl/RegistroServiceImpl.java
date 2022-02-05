package com.intermediary.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;

import com.intermediary.catalogo.mensajes.CatalogoMensajesRegistro;
import com.intermediary.dto.RegistroDTO;
import com.intermediary.dto.respuestas.RespuestaRegistroDTO;
import com.intermediary.entity.RegistroEntity;
import com.intermediary.repository.RegistroRepository;
import com.intermediary.service.RegistroService;
import com.intermediary.utils.converter.RegistroConverter;

@Service("RegistroService")
public class RegistroServiceImpl implements RegistroService{
	
	@Autowired
	@Qualifier("RegistroRepository")
	private RegistroRepository registroRepository;
	
	@Autowired
	private RegistroConverter registroConverter;

	@Override
	public ResponseEntity<RespuestaRegistroDTO> realizarRegistro(RegistroDTO datosRegistro) {
		RespuestaRegistroDTO respuesta = new RespuestaRegistroDTO();
		RegistroEntity registroEntity = null;
		try {
			registroEntity = registroConverter.ModelToEntity(datosRegistro);
			registroRepository.save(registroEntity);
		}catch (DataAccessException e) {
		} catch (BindException e) {
			e.printStackTrace();
		}
		respuesta.setRegistro(datosRegistro);
		respuesta.setMensaje(CatalogoMensajesRegistro.REGISTRO_EXITOSO);
		return new ResponseEntity<RespuestaRegistroDTO>(respuesta, HttpStatus.CREATED);
	}

}
