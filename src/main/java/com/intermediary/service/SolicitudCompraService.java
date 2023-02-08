package com.intermediary.service;

import com.intermediary.entity.SolicitudCompraEntity;

public interface SolicitudCompraService {

	public SolicitudCompraEntity crearSolicitudCompra();
	
	public SolicitudCompraEntity cancelarSolicitud(Long idSolicitud);
}
