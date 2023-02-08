package com.intermediary.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.intermediary.entity.SolicitudCompraEntity;
import com.intermediary.enums.EstadoEntidad;
import com.intermediary.enums.EstadoNegociacion;
import com.intermediary.repository.SolicitudCompraRepository;
import com.intermediary.service.SolicitudCompraService;

@Service("SolicitudCompraService")
public class SolicitudCompraServiceImpl implements SolicitudCompraService{
	
	@Autowired
	@Qualifier("SolicitudCompraRepository")
	private SolicitudCompraRepository solicitudCompraRepository;

	@Override
	public SolicitudCompraEntity crearSolicitudCompra() {
		SolicitudCompraEntity solicitudCompra = new SolicitudCompraEntity();
		solicitudCompra.setEstado(EstadoEntidad.ACTIVO);
		solicitudCompra.setEstadoNegociacion(EstadoNegociacion.ACTIVO);
		return solicitudCompraRepository.save(solicitudCompra);
	}

	@Override
	public SolicitudCompraEntity cancelarSolicitud(Long idSolicitud) {
		SolicitudCompraEntity solicitudCompra = solicitudCompraRepository.findById(idSolicitud).orElseThrow();
		solicitudCompra.setEstadoNegociacion(EstadoNegociacion.RECHAZADA);
		return solicitudCompraRepository.save(solicitudCompra);
	}

}
