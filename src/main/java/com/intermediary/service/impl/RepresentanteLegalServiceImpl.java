package com.intermediary.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;

import com.intermediary.catalogo.mensajes.CatalogoMensajesRepresentanteLegal;
import com.intermediary.dto.RepresentanteLegalDTO;
import com.intermediary.entity.RepresentanteLegalEntity;
import com.intermediary.exception.DataException;
import com.intermediary.repository.RepresentanteLegalRepository;
import com.intermediary.service.RepresentanteLegalService;
import com.intermediary.utils.converter.RepresentanteLegalConverter;

@Service("RepresentanteLegalService")
public class RepresentanteLegalServiceImpl implements RepresentanteLegalService{
	
	@Autowired
	@Qualifier("RepresentanteLegalRepository")
	private RepresentanteLegalRepository representanteLegalRepository;
	
	@Autowired
	private RepresentanteLegalConverter representanteConverter;

	@Override
	@Transactional
	public ResponseEntity<?> registrarRepresentantelegal(RepresentanteLegalDTO datosRepresentanteLegal) {
		Map<String, Object> respuesta = new HashMap<>();
		RepresentanteLegalEntity representanteGuardar = null;
		try {
			representanteGuardar = representanteConverter.ModelToEntity(datosRepresentanteLegal);
			representanteLegalRepository.save(representanteGuardar);
		} catch (DataException e) {
			throw new DataException(CatalogoMensajesRepresentanteLegal.ERROR_GUARDAR_REPRESENTANTE, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (BindException e) {
			throw new DataException(CatalogoMensajesRepresentanteLegal.ERROR_SERVIDOR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		respuesta.put(CatalogoMensajesRepresentanteLegal.MENSAJE, CatalogoMensajesRepresentanteLegal.REPRESENTANTE_CREADO_CON_EXITO);
		respuesta.put(CatalogoMensajesRepresentanteLegal.REPRESENTANTE, datosRepresentanteLegal);
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

}
