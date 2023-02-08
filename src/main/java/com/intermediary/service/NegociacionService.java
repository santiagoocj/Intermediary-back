package com.intermediary.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.intermediary.dto.NegocioDTO;
import com.intermediary.entity.NegocioEntity;

public interface NegociacionService {

	public ResponseEntity<Map<String, Object>> crearNegociacion(Long idComprador, Long idProducto, NegocioDTO negocio);
	
	public List<NegocioEntity> listarNegociosEmpresa(Long idEmpresa);
	
	public ResponseEntity<Map<String, Object>> cancelarNegociacion(Long idSolicitudCompra);
	
	public ResponseEntity<Map<String, Object>> aceptarNegociacion(Long idNegocio, String contraOfertaVendedor);
}
