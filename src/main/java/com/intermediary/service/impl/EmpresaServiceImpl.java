package com.intermediary.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;

import com.intermediary.catalogo.mensajes.CatalogoMensajesEmpresa;
import com.intermediary.dto.EmpresaDTO;
import com.intermediary.entity.EmpresaEntity;
import com.intermediary.exception.DataException;
import com.intermediary.repository.EmpresaRepository;
import com.intermediary.repository.RepresentanteLegalRepository;
import com.intermediary.service.EmpresaService;
import com.intermediary.utils.converter.EmpresaConverter;

@Service("EmpresaService")
public class EmpresaServiceImpl implements EmpresaService{

	@Autowired
	@Qualifier("EmpresaRepository")
	private EmpresaRepository empresaRepository;
	
	@Autowired
	@Qualifier("RepresentanteLegalRepository")
	private RepresentanteLegalRepository representanteLegalRepository;
	
	@Autowired
	private EmpresaConverter converter;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<?> encontrarTodos() {
		List<EmpresaDTO> empresasDTOs = null;
		Map<String, Object> response = new HashMap<>();
		try {
			empresasDTOs = converter.EntityToModel(empresaRepository.findAll());
		} catch (DataAccessException e) {
			throw new DataException(CatalogoMensajesEmpresa.ERROR_CONSULTA_EMPRESAS, HttpStatus.NOT_FOUND);
		} catch (BindException e) {
			throw new DataException(CatalogoMensajesEmpresa.ERROR_SERVIDOR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(empresasDTOs == null || empresasDTOs.isEmpty()) {
			response.put(CatalogoMensajesEmpresa.MENSAJE, CatalogoMensajesEmpresa.SIN_EMPRESAS_EN_BASE_DATOS);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return  new ResponseEntity<List<EmpresaDTO>>(empresasDTOs, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<?> registroEmpresa(EmpresaDTO datosEmpresaRegistro) {
		Map<String, Object> response = new HashMap<>();
		EmpresaEntity empresa = null;
		try {
			empresa = converter.modelToEntity(datosEmpresaRegistro);
			empresaRepository.save(empresa);
		} catch (DataAccessException e) {
			throw new DataException(CatalogoMensajesEmpresa.ERROR_INSERTAR_EMPRESAS, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (BindException e) {
			throw new DataException(CatalogoMensajesEmpresa.ERROR_SERVIDOR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put(CatalogoMensajesEmpresa.MENSAJE, CatalogoMensajesEmpresa.EMPRESA_CREADA_CON_EXITO);
		response.put(CatalogoMensajesEmpresa.EMPRESA, datosEmpresaRegistro);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
