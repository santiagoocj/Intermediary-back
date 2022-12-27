package com.intermediary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intermediary.dto.InformacionCompraMembresiaEmpresaDTO;
import com.intermediary.service.impl.VigenciaServiceImpl;

@RestController
@RequestMapping("/api")
public class VigenciaMembresiaController {
	
	@Autowired
	private VigenciaServiceImpl vigenciaService;
	
	@Secured("ROLE_ADMINISTRADOR")
	@GetMapping("/lista-vigencia/inactiva")
	public List<InformacionCompraMembresiaEmpresaDTO> listarCompraMembresiaConVigenciaInactiva(){
		return vigenciaService.listarVigenciasInactivas();
	}

}
