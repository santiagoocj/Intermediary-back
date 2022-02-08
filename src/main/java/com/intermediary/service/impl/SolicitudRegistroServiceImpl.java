package com.intermediary.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;

import com.intermediary.entity.RegistroEntity;
import com.intermediary.entity.SolicitudRegistroEntity;
import com.intermediary.enums.EstadoSolicitudEnum;
import com.intermediary.repository.SolicitudRegistroRepository;
import com.intermediary.service.SolicitudRegistroService;

@Service("SolicitudRegistroService")
public class SolicitudRegistroServiceImpl implements SolicitudRegistroService{
	
	@Autowired
	@Qualifier("SolicitudRegistroRepository")
	private SolicitudRegistroRepository solicitudRegistroRepository;

	@Override
	public void guardarSolicitud(RegistroEntity registro) throws BindException {
		SolicitudRegistroEntity solicitudGuardar = new SolicitudRegistroEntity();
		solicitudGuardar.setNombre(registro.getNombreEmpresa());
		solicitudGuardar.setEstadoSolicitud(EstadoSolicitudEnum.PENDIENTE.toString());
		solicitudGuardar.setRegistro(registro);
		solicitudRegistroRepository.save(solicitudGuardar);
	}

}
