package com.intermediary.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.intermediary.entity.MembresiaEntity;
import com.intermediary.repository.MembresiaRepository;
import com.intermediary.service.MembresiaService;

@Service("MembresiaService")
public class MembresiaServiceImpl implements MembresiaService{
	
	@Autowired
	@Qualifier("MembresiaRepository")
	private MembresiaRepository membresiaRepository;

	@Override
	public MembresiaEntity buscarXId(Long id) {
		return membresiaRepository.findById(id).orElse(null);
	}

	@Override
	public MembresiaEntity obtenerMembresiaBasica() {
		return membresiaRepository.obtenerMembresiaBasica();
	}

}
