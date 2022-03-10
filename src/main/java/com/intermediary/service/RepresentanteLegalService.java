package com.intermediary.service;

import org.springframework.http.ResponseEntity;

import com.intermediary.dto.RepresentanteLegalDTO;
import com.intermediary.entity.RepresentanteLegalEntity;

public interface RepresentanteLegalService {
	
	public ResponseEntity<?> registrarRepresentantelegal(RepresentanteLegalDTO datosRepresentanteLegal);
	
	public ResponseEntity<?> buscar(Long id);
	
	public void inactivar(Long id);
	
	public RepresentanteLegalEntity buscarXId(Long id);
}
