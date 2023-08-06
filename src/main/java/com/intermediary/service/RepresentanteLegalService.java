package com.intermediary.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;

import com.intermediary.dto.RepresentanteLegalDTO;
import com.intermediary.entity.RepresentanteLegalEntity;

public interface RepresentanteLegalService {
	
	public Map<String, Object> registrarRepresentantelegal(RepresentanteLegalDTO datosRepresentanteLegal) throws BindException;
	
	public ResponseEntity<Map<String, Object>> buscar(Long id) throws BindException;
	
	public void inactivar(Long id);
	
	public RepresentanteLegalEntity buscarXId(Long id);
}
