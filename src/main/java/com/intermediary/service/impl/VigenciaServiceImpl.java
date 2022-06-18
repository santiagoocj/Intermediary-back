package com.intermediary.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intermediary.entity.MembresiaEntity;
import com.intermediary.entity.VigenciaEntity;
import com.intermediary.repository.VigenciaRepository;
import com.intermediary.service.VigenciaService;

@Service("VigenciaService")
public class VigenciaServiceImpl implements VigenciaService{
	
	@Autowired
	private VigenciaRepository vigenciaRepository;

	@Override
	public VigenciaEntity registroVigencia(MembresiaEntity membresia) {
		VigenciaEntity datosAGuardar = new VigenciaEntity(membresia);
		return vigenciaRepository.save(datosAGuardar);
	}

}
