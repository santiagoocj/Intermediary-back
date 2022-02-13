package com.intermediary.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;

import com.intermediary.catalogo.mensajes.CatalogoMensajesSolicitudRegistro;
import com.intermediary.dto.SolicitudRegistroDTO;
import com.intermediary.dto.respuestas.ListarSolicitudRegistroDTO;
import com.intermediary.entity.RegistroEntity;
import com.intermediary.entity.SolicitudRegistroEntity;
import com.intermediary.enums.EstadoSolicitudEnum;
import com.intermediary.exception.DataException;
import com.intermediary.repository.SolicitudRegistroRepository;
import com.intermediary.service.SolicitudRegistroService;
import com.intermediary.utils.converter.SolicitudRegistroConverter;

@Service("SolicitudRegistroService")
public class SolicitudRegistroServiceImpl implements SolicitudRegistroService{
	
	@Autowired
	@Qualifier("SolicitudRegistroRepository")
	private SolicitudRegistroRepository solicitudRegistroRepository;
	
	@Autowired
	private SolicitudRegistroConverter solicitudRegistroConverter;

	@Override
	public void guardarSolicitud(RegistroEntity registro) throws BindException {
		SolicitudRegistroEntity solicitudGuardar = new SolicitudRegistroEntity();
		solicitudGuardar.setNombre(registro.getNombreEmpresa());
		solicitudGuardar.setEstadoSolicitud(EstadoSolicitudEnum.PENDIENTE.toString());
		solicitudGuardar.setRegistro(registro);
		solicitudRegistroRepository.save(solicitudGuardar);
	}

	@Override
	public ResponseEntity<ListarSolicitudRegistroDTO> listarTodo() {
		List<SolicitudRegistroDTO> solicitudesListar = null;
		ListarSolicitudRegistroDTO respuesta = null;
		String mensajeRespuesta = CatalogoMensajesSolicitudRegistro.SOLICITUDES_DISPONIBLES;
		try {
			solicitudesListar = solicitudRegistroConverter.EntityToModel(solicitudRegistroRepository.findAll());
			if(solicitudesListar == null) {
				mensajeRespuesta = CatalogoMensajesSolicitudRegistro.NO_HAY_SOLICITUDES;
			}
			respuesta = ListarSolicitudRegistroDTO.builder().mensaje(mensajeRespuesta)
					.solicitudesRegistro(solicitudesListar).build();
		} catch (BindException e) {
			throw new DataException(CatalogoMensajesSolicitudRegistro.ERROR_LISTAR_SOLICITUDES_REGISTRO, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ListarSolicitudRegistroDTO>(respuesta, HttpStatus.OK);
	}

}
