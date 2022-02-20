package com.intermediary.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;

import com.intermediary.catalogo.mensajes.CatalogoMensajesGenerales;
import com.intermediary.catalogo.mensajes.CatalogoMensajesSolicitudRegistro;
import com.intermediary.dto.CambiarEstadoSolicitudRegistroDTO;
import com.intermediary.dto.EmailDTO;
import com.intermediary.dto.SolicitudRegistroDTO;
import com.intermediary.dto.respuestas.ListarSolicitudRegistroDTO;
import com.intermediary.dto.respuestas.RespuestaEstadoSolicitudRegistroDTO;
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
	
	@Autowired
	private EmailServiceImpl emailServiceImpl;

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

	@Override
	public ResponseEntity<RespuestaEstadoSolicitudRegistroDTO> modificar(Long id, CambiarEstadoSolicitudRegistroDTO solicitudCambiar) {
		SolicitudRegistroEntity solicitudRegistro = null;
		String mensajeRespuesta = CatalogoMensajesSolicitudRegistro.SOLICITUD_MODIFICADA;
		RespuestaEstadoSolicitudRegistroDTO respuesta = new RespuestaEstadoSolicitudRegistroDTO();
		try {
			solicitudRegistro = solicitudRegistroRepository.findById(id).orElse(null);
			modificarEstadoSolicitudRegistro(solicitudRegistro, solicitudCambiar);
			EmailDTO email = construirEmail(solicitudCambiar.getContenidoCorreoEstadoSolicitud(), solicitudRegistro.getRegistro().getEmail());
			emailServiceImpl.sendEmail(email);
			respuesta.setMensaje(mensajeRespuesta);
			respuesta.setIdSolicitud(id);
		} catch (BindException e) {
			throw new DataException(CatalogoMensajesSolicitudRegistro.ERROR_EDITAR_SOLICITUD_DE_REGISTRO, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<RespuestaEstadoSolicitudRegistroDTO>(respuesta, HttpStatus.OK);
	}
	
	private EmailDTO construirEmail(String contenidoCorreoEstadoSolicitud, String email) {
		EmailDTO emailEnviar = new EmailDTO();
		emailEnviar.setEmail(email);
		emailEnviar.setSubject(CatalogoMensajesGenerales.SOLICITUD_REGISTRO);
		emailEnviar.setContent(contenidoCorreoEstadoSolicitud);
		return emailEnviar;
	}

	private void modificarEstadoSolicitudRegistro(SolicitudRegistroEntity solicitud ,CambiarEstadoSolicitudRegistroDTO solicitudCambiar) throws BindException {
		if(solicitudCambiar.getNombreSolicitud() != null) {
			solicitud.setNombre(solicitudCambiar.getNombreSolicitud());
		}
		solicitud.setEstadoSolicitud(solicitudCambiar.getEstado());
		solicitudRegistroRepository.save(solicitud);
	}

	@Override
	public boolean existeSolicitud(Long id) {
		return solicitudRegistroRepository.findById(id).orElse(null) == null? false:true;
	}

}
