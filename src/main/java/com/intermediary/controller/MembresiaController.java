package com.intermediary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intermediary.entity.MembresiaEntity;
import com.intermediary.service.impl.MembresiaServiceImpl;

@RestController
@RequestMapping("/api")
public class MembresiaController {
	
	@Autowired
	private MembresiaServiceImpl membresiaService;
	
	@GetMapping("/membresia/lista")
	public List<MembresiaEntity> listarMembresias(){
		return membresiaService.listarMembresias();
	}

}
