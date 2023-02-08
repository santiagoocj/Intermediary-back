package com.intermediary.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intermediary.dto.NegocioDTO;
import com.intermediary.entity.NegocioEntity;
import com.intermediary.service.impl.NegociacionServiceImpl;

@RestController
@RequestMapping("/api")
public class NegociacionController {
	
	@Autowired
	private NegociacionServiceImpl negociacionServiceImpl;
	
	@Secured("ROLE_EMPRESA")
	@PostMapping("/negociacion/crear/{id-comprador}/{id-producto}")
	public ResponseEntity<Map<String, Object>> crearNegociacion(@PathVariable(name = "id-comprador") Long idComprador, @PathVariable(name = "id-producto") Long idProducto, @RequestBody NegocioDTO negocio){
		return negociacionServiceImpl.crearNegociacion(idComprador, idProducto, negocio);
	}
	
	@Secured("ROLE_EMPRESA")
	@GetMapping("/negociacion/{id-empresa}")
	public List<NegocioEntity> lstarNegociosDeLaEmpresa(@PathVariable(name = "id-empresa") Long idEmpresa) {
		return negociacionServiceImpl.listarNegociosEmpresa(idEmpresa);
	}
	
	@Secured("ROLE_EMPRESA")
	@PutMapping("/negociacion/cancelar/{id-solicitud-compra}")
	public ResponseEntity<Map<String, Object>> cancelarNegocio(@PathVariable(name = "id-negocio") Long idSolicitudCompra){
		return negociacionServiceImpl.cancelarNegociacion(idSolicitudCompra);
	}
	
	@Secured("ROLE_EMPRESA")
	@PutMapping("/negociacion/aceptar/{id-negocio}")
	public ResponseEntity<Map<String, Object>> aceptarNegociacion(@PathVariable(name = "id-negocio") Long idNegocio, @RequestBody String contraOfertaVendedor){
		return negociacionServiceImpl.aceptarNegociacion(idNegocio, contraOfertaVendedor);
	}
	

}
