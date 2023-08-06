package com.intermediary.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
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
import com.intermediary.entity.RepresentanteLegalEntity;
import com.intermediary.entity.SolicitudRegistroEntity;
import com.intermediary.enums.EstadoSolicitudEnum;
import com.intermediary.exception.DataException;
import com.intermediary.repository.SolicitudRegistroRepository;
import com.intermediary.service.SolicitudRegistroService;
import com.intermediary.utils.converter.SolicitudRegistroConverter;

@Service("SolicitudRegistroService")
public class SolicitudRegistroServiceImpl implements SolicitudRegistroService{
	
	private static Logger logger = LogManager.getLogger(SolicitudRegistroServiceImpl.class);
	
	@Autowired
	@Qualifier("SolicitudRegistroRepository")
	private SolicitudRegistroRepository solicitudRegistroRepository;
	
	@Autowired
	private SolicitudRegistroConverter solicitudRegistroConverter;
	
	@Autowired(required = true)
	private EmailServiceImpl emailServiceImpl;
	
	@Autowired
	private RegistroServiceImpl registroEmpresaServiceImpl;
	
	@Autowired
	private RepresentanteLegalServiceImpl representanteLegalServiceImpl;

	@Override
	public ListarSolicitudRegistroDTO listarTodo() throws BindException {
		List<SolicitudRegistroDTO> solicitudesListar = null;
		ListarSolicitudRegistroDTO respuesta = null;
		String mensajeRespuesta = CatalogoMensajesSolicitudRegistro.SOLICITUDES_DISPONIBLES;
		solicitudesListar = solicitudRegistroConverter.EntityToModel(solicitudRegistroRepository.findAll());
		if(solicitudesListar == null) {
			logger.info("Sin solicitudes de registro a listar");
			mensajeRespuesta = CatalogoMensajesSolicitudRegistro.NO_HAY_SOLICITUDES;
		}
		respuesta = ListarSolicitudRegistroDTO.builder().mensaje(mensajeRespuesta)
				.solicitudesRegistro(solicitudesListar).build();
		return respuesta;
	}

	@Override
	public RespuestaEstadoSolicitudRegistroDTO modificar(Long id, CambiarEstadoSolicitudRegistroDTO solicitudCambiar) throws BindException {
		SolicitudRegistroEntity solicitudRegistro = null;
		String mensajeRespuesta = CatalogoMensajesSolicitudRegistro.SOLICITUD_MODIFICADA;
		RespuestaEstadoSolicitudRegistroDTO respuesta = new RespuestaEstadoSolicitudRegistroDTO();
		solicitudRegistro = solicitudRegistroRepository.findById(id).orElse(null);
		modificarEstadoSolicitudRegistro(solicitudRegistro, solicitudCambiar);
		EmailDTO email = emailServiceImpl.construirEmail(solicitudRegistro.getRegistro().getEmail(), CatalogoMensajesGenerales.SOLICITUD_REGISTRO, solicitudCambiar.getContenidoCorreoEstadoSolicitud());
		emailServiceImpl.sendEmail(email);
		respuesta.setMensaje(mensajeRespuesta);
		respuesta.setIdSolicitud(id);
		return respuesta;
	}
	

	private void modificarEstadoSolicitudRegistro(SolicitudRegistroEntity solicitud ,CambiarEstadoSolicitudRegistroDTO solicitudCambiar) throws BindException {
		solicitud.setEstadoSolicitud(solicitudCambiar.getEstado());
		solicitudRegistroRepository.save(solicitud);
	}

	@Override
	public boolean existeSolicitud(Long id) {
		return solicitudRegistroRepository.findById(id).orElse(null) == null? false:true;
	}

	@Override
	public Map<String, String> crearSolicitud(Long idEmpresa, Long idRepresentante) throws BindException {
		RegistroEntity empresa = registroEmpresaServiceImpl.buscarXId(idEmpresa);
		RepresentanteLegalEntity representante = representanteLegalServiceImpl.buscarXId(idRepresentante);
		validarRepresentanteSolicitudRegistro(empresa);
		guardarSolicitud(empresa, representante);
		return crearRespuestaExitoCrearSolicitud();
	}
	
	private Map<String, String> crearRespuestaExitoCrearSolicitud(){
		Map<String, String> respuesta = new HashMap<>();
		respuesta.put(CatalogoMensajesGenerales.MENSAJE, CatalogoMensajesSolicitudRegistro.SOLICITUD_REGISTRO_ACEPTADA);
		logger.info("Solicitud de registro aceptada");
		return respuesta;
	}
	
	private void guardarSolicitud(RegistroEntity registro, RepresentanteLegalEntity representante) throws BindException {
		SolicitudRegistroEntity solicitudGuardar = new SolicitudRegistroEntity();
		solicitudGuardar.setNombre(registro.getNombreEmpresa());
		solicitudGuardar.setEstadoSolicitud(EstadoSolicitudEnum.PENDIENTE);
		solicitudGuardar.setRegistro(registro);
		solicitudGuardar.setRepresentanteLegal(representante);
		solicitudRegistroRepository.save(solicitudGuardar);
	}

	@Override
	public SolicitudRegistroEntity findById(Long idSolicitudRegistro) {
		return solicitudRegistroRepository.findById(idSolicitudRegistro).orElseThrow();
	}

	@Override
	public void validarEstadoSolicitud(SolicitudRegistroEntity solicitudRegistro) {
		if(solicitudRegistro.getEstadoSolicitud() != EstadoSolicitudEnum.APROBADA) {
			throw new DataException(CatalogoMensajesSolicitudRegistro.EMPRESA_NO_VALIDADA, HttpStatus.BAD_REQUEST);
		}
	}
	
	private void validarRepresentanteSolicitudRegistro(RegistroEntity empresa) {
		SolicitudRegistroEntity solicitudRegistroEntity = solicitudRegistroRepository.findByRegistro(empresa);
		if(solicitudRegistroEntity != null) {
			if(solicitudRegistroEntity.getRepresentanteLegal() != null) {
				logger.info("Representante legal ya registrado");
				throw new DataException(CatalogoMensajesSolicitudRegistro.REPRESENTANTE_YA_REGISTRADO, HttpStatus.BAD_REQUEST);
			}
		}
		
	}

}
