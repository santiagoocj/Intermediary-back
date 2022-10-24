package com.intermediary.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;

import com.intermediary.dto.CategoriaDTO;
import com.intermediary.entity.CategoriaEntity;
import com.intermediary.repository.CategoriaRepository;
import com.intermediary.service.CategoriaService;
import com.intermediary.utils.converter.CategoriaConverter;

@Service("CategoriaService")
public class CategoriaServiceImpl implements CategoriaService {
	
	@Autowired
	@Qualifier("CategoriaRepository")
	private CategoriaRepository repository;
	
	@Autowired
	private CategoriaConverter categoriaConverter;

	@Override
	public CategoriaEntity buscarXId(Long idCategoria) {
		return repository.findById(idCategoria).orElse(null);
	}

	@Override
	public List<CategoriaDTO> obtenerTodasLasCategorias() throws BindException {
		return categoriaConverter.EntityToModel(repository.findAll());
	}

}
