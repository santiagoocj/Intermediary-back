package com.intermediary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intermediary.dto.CategoriaDTO;
import com.intermediary.service.impl.CategoriaServiceImpl;

@Controller
@RequestMapping("/api")
public class CategoriaController {
	
	@Autowired
	private CategoriaServiceImpl categoriaServiceImpl;
	
	@GetMapping("/categorias")
	public ResponseEntity<List<CategoriaDTO>> obtenerTodasLasCategorias() throws BindException{
		List<CategoriaDTO> categoriasEncontradas = categoriaServiceImpl.obtenerTodasLasCategorias();
		return new ResponseEntity<>(categoriasEncontradas, HttpStatus.OK);
	}

}
