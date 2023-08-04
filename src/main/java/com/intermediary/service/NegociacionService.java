package com.intermediary.service;

import java.util.List;
import java.util.Map;

import com.intermediary.dto.NegocioDTO;
import com.intermediary.entity.NegocioEntity;

public interface NegociacionService {

	public Map<String, Object> crearNegociacion(Long idComprador, Long idProducto, NegocioDTO negocio);
	
	public List<NegocioEntity> listarNegociosEmpresa(Long idEmpresa);
	
	public Map<String, Object> cancelarNegociacion(Long idSolicitudCompra);
	
	public Map<String, Object> aceptarNegociacion(Long idNegocio, String contraOfertaVendedor);
}
