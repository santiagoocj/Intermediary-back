package com.intermediary.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.intermediary.entity.CategoriaEntity;
import com.intermediary.repository.CategoriaRepository;
import com.intermediary.service.CategoriaService;

@Service("CategoriaService")
public class CategoriaServiceImpl implements CategoriaService {
	
	@Autowired
	@Qualifier("CategoriaRepository")
	private CategoriaRepository repository;

	@Override
	public CategoriaEntity buscarXId(Long idCategoria) {
		return repository.findById(idCategoria).orElse(null);
	}

}
