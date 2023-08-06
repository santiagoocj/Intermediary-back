package com.intermediary.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	private static Logger logger = LogManager.getLogger(RepresentanteLegalServiceImpl.class);
	
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
	public Map<String, Object> registrarRepresentantelegal(RepresentanteLegalDTO datosRepresentanteLegal) throws BindException {
		Map<String, Object> respuesta = new HashMap<>();
		RepresentanteLegalEntity representanteGuardar = null;
		representanteGuardar = representanteConverter.ModelToEntity(datosRepresentanteLegal);
		representanteGuardar = representanteLegalRepository.save(representanteGuardar);
		datosRepresentanteLegal.setId(representanteGuardar.getId());
		respuesta.put(CatalogoMensajesRepresentanteLegal.MENSAJE, CatalogoMensajesRepresentanteLegal.REPRESENTANTE_CREADO_CON_EXITO);
		respuesta.put(CatalogoMensajesRepresentanteLegal.REPRESENTANTE, datosRepresentanteLegal);
		return respuesta;
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<Map<String, Object>> buscar(Long id) {
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
		logger.info("Buscar por id mensaje de retorno: " + respuesta.get(CatalogoMensajesRepresentanteLegal.MENSAJE));
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
