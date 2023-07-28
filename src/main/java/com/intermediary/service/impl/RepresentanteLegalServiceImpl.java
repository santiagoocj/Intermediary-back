package com.intermediary.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;

import com.intermediary.catalogo.mensajes.CatalogoMensajesRepresentanteLegal;
import com.intermediary.dto.RepresentanteLegalDTO;
import com.intermediary.entity.RepresentanteLegalEntity;
import com.intermediary.enums.EstadoEntidad;
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
	@Qualifier("RegistroService")
	private RegistroServiceImpl registroServiceImpl;
	
	@Autowired
	private RepresentanteLegalConverter representanteConverter;

	@Override
	@Transactional
	public ResponseEntity<?> registrarRepresentantelegal(RepresentanteLegalDTO datosRepresentanteLegal) {
		Map<String, Object> respuesta = new HashMap<>();
		RepresentanteLegalEntity representanteGuardar = null;
		try {
			representanteGuardar = representanteConverter.ModelToEntity(datosRepresentanteLegal);
			representanteGuardar = representanteLegalRepository.save(representanteGuardar);
			datosRepresentanteLegal.setId(representanteGuardar.getId());
		} catch (DataException e) {
			throw new DataException(CatalogoMensajesRepresentanteLegal.ERROR_GUARDAR_REPRESENTANTE, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (BindException e) {
			throw new DataException(CatalogoMensajesRepresentanteLegal.ERROR_SERVIDOR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		respuesta.put(CatalogoMensajesRepresentanteLegal.MENSAJE, CatalogoMensajesRepresentanteLegal.REPRESENTANTE_CREADO_CON_EXITO);
		respuesta.put(CatalogoMensajesRepresentanteLegal.REPRESENTANTE, datosRepresentanteLegal);
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<?> buscar(Long id) {
		Map<String, Object> respuesta = new HashMap<>();
		RepresentanteLegalEntity representanteLegalEntity = null;
		representanteLegalEntity = representanteLegalRepository.findById(id).orElse(null);
		if(representanteLegalEntity != null) {
			RepresentanteLegalDTO representanteMostrar = null;
			try{
				representanteMostrar = representanteConverter.EntityToModel(representanteLegalEntity);
			}catch (BindException e) {
				throw new DataException(CatalogoMensajesRepresentanteLegal.ERROR_SERVIDOR, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			respuesta.put(CatalogoMensajesRepresentanteLegal.REPRESENTANTE, representanteMostrar);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
		}
		respuesta.put(CatalogoMensajesRepresentanteLegal.MENSAJE, CatalogoMensajesRepresentanteLegal.CLIENTE_NO_ENCONTRADO);
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
	}

	@Override
	public void inactivar(Long id) {
		RepresentanteLegalEntity representante = representanteLegalRepository.findById(id).orElse(null);
		if(representante != null) {
			representante.setEstado(EstadoEntidad.INACTIVO);
			representanteLegalRepository.save(representante);
		}
	}

	@Override
	public RepresentanteLegalEntity buscarXId(Long id) {
		return representanteLegalRepository.findById(id).orElse(null);
	}

}
