package com.intermediary.service;

import org.springframework.validation.BindException;

import com.intermediary.entity.RegistroEntity;

public interface SolicitudRegistroService {
	
	public void guardarSolicitud(RegistroEntity registro) throws BindException;
}
