package com.intermediary.controller;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.intermediary.entity.MembresiaEntity;
import com.intermediary.exception.DataException;
import com.intermediary.service.impl.MembresiaServiceImpl;

@RestController
@RequestMapping("/api")
public class MembresiaController {
	
	private static Logger logger = LogManager.getLogger(MembresiaController.class);
	
	@Autowired
	private MembresiaServiceImpl membresiaService;
	
	@GetMapping("/membresia/lista")
	public List<MembresiaEntity> listarMembresias(){
		return membresiaService.listarMembresias();
	}

	@PutMapping("/membresia/actualizar")
	public ResponseEntity<Map<String, Object>> actualizarMembresia(@RequestParam("idEmpresa") Long idEmpresa, @RequestParam("idMembresia") Long idMembresia, @RequestParam("comprobantePago") MultipartFile comprobantePago) {
		return new ResponseEntity<Map<String,Object>>(membresiaService.actualizarMembresia(idEmpresa, idMembresia, comprobantePago), HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMINISTRADOR")
	@PutMapping("/membresia/activar")
	public ResponseEntity<Map<String, Object>> activarMembresia(@RequestParam("idEmpresa") Long idEMpresa, @RequestParam("idVigencia") Long idVigencia){
		try {
			return new ResponseEntity<Map<String,Object>>(membresiaService.activarMembresia(idEMpresa, idVigencia), HttpStatus.OK);
		}catch (Exception e) {
			logger.error("Error activando la membresia para la empresa con id " + idEMpresa + ". Error " + e.getMessage());
			throw new DataException("Error " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
