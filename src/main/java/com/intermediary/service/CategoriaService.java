package com.intermediary.service;

import java.util.List;

import org.springframework.validation.BindException;

import com.intermediary.dto.CategoriaDTO;
import com.intermediary.entity.CategoriaEntity;

public interface CategoriaService {
	
	public CategoriaEntity buscarXId(Long idCategoria);
	
	public List<CategoriaDTO> obtenerTodasLasCategorias() throws BindException;

}
