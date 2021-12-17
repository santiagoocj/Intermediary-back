package com.intermediary.service;

import org.springframework.http.ResponseEntity;

import com.intermediary.dto.RepresentanteLegalDTO;

public interface RepresentanteLegalService {
	
	public ResponseEntity<?> registrarRepresentantelegal(RepresentanteLegalDTO datosRepresentanteLegal);
}
