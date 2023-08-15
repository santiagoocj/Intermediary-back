package com.intermediary.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;

import com.intermediary.catalogo.mensajes.CatalogoMensajesEmpresa;
import com.intermediary.catalogo.mensajes.CatalogoMensajesGenerales;
import com.intermediary.dto.EmpresaDTO;
import com.intermediary.dto.InfoBasicaUsuarioDTO;
import com.intermediary.dto.respuestas.RespuestaEmpresaDTO;
import com.intermediary.entity.EmpresaEntity;
import com.intermediary.entity.RegistroEntity;
import com.intermediary.entity.RepresentanteLegalEntity;
import com.intermediary.entity.SolicitudRegistroEntity;
import com.intermediary.entity.VigenciaEntity;
import com.intermediary.enums.EstadoEntidad;
import com.intermediary.enums.RoleEnum;
import com.intermediary.exception.DataException;
import com.intermediary.repository.EmpresaRepository;
import com.intermediary.service.EmpresaService;
import com.intermediary.utils.converter.EmpresaConverter;

@Service("EmpresaService")
public class EmpresaServiceImpl implements EmpresaService{
	
	private static Logger logger = LogManager.getLogger(EmpresaServiceImpl.class);

	@Autowired
	@Qualifier("EmpresaRepository")
	private EmpresaRepository empresaRepository;
	
	@Autowired
	@Qualifier("RepresentanteLegalService")
	private RepresentanteLegalServiceImpl representanteLegalServiceImpl;
	
	@Autowired
	@Qualifier("VigenciaService")
	private VigenciaServiceImpl vigenciaServiceImpl;
	
	@Autowired
	@Qualifier("SolicitudRegistroService")
	private SolicitudRegistroServiceImpl solicitudRegistroServiceImpl;
	
	@Autowired
	@Qualifier("UsuarioService")
	private UsuarioServiceImpl usuarioService;
	
	@Autowired
	@Qualifier("RoleService")
	private RoleServiceImpl roleService;
	
	@Autowired
	private EmpresaConverter converter;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<?> encontrarTodos() {
		List<EmpresaDTO> empresasDTOs = null;
		Map<String, Object> response = new HashMap<>();
		try {
			empresasDTOs = converter.EntityToModel(empresaRepository.findAll());
		} catch (DataAccessException e) {
			logger.error(() -> "Error consultando empresas "+ e.getMessage());
			throw new DataException(CatalogoMensajesEmpresa.ERROR_CONSULTA_EMPRESAS, HttpStatus.NOT_FOUND);
		} catch (BindException e) {
			logger.error(() -> "Error desconocido listando empresas "+ e.getMessage());
			throw new DataException(CatalogoMensajesEmpresa.ERROR_SERVIDOR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(empresasDTOs == null || empresasDTOs.isEmpty()) {
			response.put(CatalogoMensajesGenerales.MENSAJE, CatalogoMensajesEmpresa.SIN_EMPRESAS_EN_BASE_DATOS);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		return  new ResponseEntity<>(empresasDTOs, HttpStatus.OK);
	}

	@Override
	@Transactional
	public RespuestaEmpresaDTO registroEmpresa(Long idSolicitudRegistro, InfoBasicaUsuarioDTO infoBasicaUsuario) throws BindException {
		SolicitudRegistroEntity solicitudRegistro = solicitudRegistroServiceImpl.findById(idSolicitudRegistro);
		solicitudRegistroServiceImpl.validarEstadoSolicitud(solicitudRegistro);
		RegistroEntity informacionEmpresa = solicitudRegistro.getRegistro();
		RepresentanteLegalEntity representanteLegal = solicitudRegistro.getRepresentanteLegal();
		usuarioService.validarInformacionUsuario(infoBasicaUsuario);
		VigenciaEntity vigenciaMembresia = vigenciaServiceImpl.registroPrimeraVigencia();
		EmpresaEntity empresa = crearEmpresa(representanteLegal, informacionEmpresa, vigenciaMembresia);
		agregarInformacionUsuario(empresa, infoBasicaUsuario);
		empresaRepository.save(empresa);
		return crearMensajeRetornoRegistroEmpresa(empresa);
	}
	
	private EmpresaEntity crearEmpresa(RepresentanteLegalEntity representante,  RegistroEntity registro, VigenciaEntity vigencia) {
		EmpresaEntity empresa = new EmpresaEntity();
		empresa.setNombre(registro.getNombreEmpresa());
		empresa.setNit(registro.getNit());
		empresa.setRazonSocial(registro.getRazonSocial());
		empresa.setCodigoCiu(registro.getCodigoCiu());
		empresa.setActividadPrincipal(registro.getActividadPrincipal());
		empresa.setTipoPersona(registro.getTipoPersona());
		empresa.setCelular(registro.getCelular());
		empresa.setCorreo(registro.getEmail());
		empresa.setRepresentanteLegalEntity(representante);
		empresa.setAnexo(registro.getAnexo());
		empresa.setVigenciaEntity(vigencia);
		return empresa;
	}
	
	private void agregarInformacionUsuario(EmpresaEntity empresa, InfoBasicaUsuarioDTO infoUsuario) {
		empresa.setUserName(infoUsuario.getUserName());
		empresa.setPassword(usuarioService.codificarContrasena(infoUsuario.getPassword()));
		empresa.setRoles(Arrays.asList(roleService.obtenerRolPorNombre(RoleEnum.EMPRESA_INICIAL.getRol())));
		empresa.setEnabled(true);
	}
	
	private RespuestaEmpresaDTO crearMensajeRetornoRegistroEmpresa(EmpresaEntity empresa) throws BindException{
		RespuestaEmpresaDTO retorno = new RespuestaEmpresaDTO();
		retorno.setEmpresa(converter.EntityToModel(empresa));
		retorno.setMensaje(CatalogoMensajesEmpresa.EMPRESA_CREADA_CON_EXITO);
		return retorno;
	}

	@Override
	public RespuestaEmpresaDTO editarInformacion(Long idEmpresa, EmpresaDTO empresaInformacionNueva) throws BindException {
		EmpresaEntity empresaActual = empresaRepository.findById(idEmpresa).orElse(null);
		RespuestaEmpresaDTO retorno = new RespuestaEmpresaDTO();
		if(empresaActual == null) {
			retorno.setMensaje(CatalogoMensajesEmpresa.EMPRESA_NO_EXISTE);
			logger.info(() -> "La empresa con id " + idEmpresa + " no existe");
			return retorno;
		}
		empresaActual = editarInformacionEmpresa(empresaActual, empresaInformacionNueva);
		empresaRepository.save(empresaActual);
		retorno.setMensaje(CatalogoMensajesEmpresa.EMPRESA_MODIFICADA_CON_EXITO);
		retorno.setEmpresa(converter.EntityToModel(empresaActual));
		return retorno;
	}

	private EmpresaEntity editarInformacionEmpresa(EmpresaEntity empresaActual, EmpresaDTO empresaInformacionNueva) {
		if(!empresaInformacionNueva.getNombre().isEmpty()) {
			empresaActual.setNombre(empresaInformacionNueva.getNombre());
		}
		if(!empresaInformacionNueva.getNit().isEmpty()) {
			empresaActual.setNit(empresaInformacionNueva.getNit());
		}
		if(!empresaInformacionNueva.getRazonSocial().isEmpty()) {
			empresaActual.setRazonSocial(empresaInformacionNueva.getRazonSocial());
		}
		if(!empresaInformacionNueva.getCodigoCiu().isEmpty()) {
			empresaActual.setCodigoCiu(empresaInformacionNueva.getCodigoCiu());
		}
		if(!empresaInformacionNueva.getActividadPrincipal().isEmpty()) {
			empresaActual.setActividadPrincipal(empresaInformacionNueva.getActividadPrincipal());
		}
		if(!empresaInformacionNueva.getTipoPersona().isEmpty()) {
			empresaActual.setTipoPersona(empresaInformacionNueva.getTipoPersona());
		}
		if(!empresaInformacionNueva.getCorreo().isEmpty()) {
			empresaActual.setCorreo(empresaInformacionNueva.getCorreo());
		}
		if(!empresaInformacionNueva.getCelular().isEmpty()) {
			empresaActual.setCelular(empresaInformacionNueva.getCelular());
		}
		return empresaActual;
	}

	@Override
	public RespuestaEmpresaDTO inactivar(Long idEmpresa) {
		RespuestaEmpresaDTO respuestaRetorno = new RespuestaEmpresaDTO();
		EmpresaEntity empresa = empresaRepository.findById(idEmpresa).orElse(null);
		if(empresa == null) {
			respuestaRetorno.setMensaje(CatalogoMensajesEmpresa.EMPRESA_NO_EXISTE);
		}else {
			empresa.setEstado(EstadoEntidad.INACTIVO);
			Long idRepresentante = empresa.getRepresentanteLegalEntity().getId();
			representanteLegalServiceImpl.inactivar(idRepresentante);
			empresaRepository.save(empresa);
			logger.info(() -> "Empresa con id " + idEmpresa + " se inactivó de manera exitosa");
			respuestaRetorno.setMensaje(CatalogoMensajesEmpresa.EMPRESA_ELIMINADA_EXITOSA);
		}
		return respuestaRetorno;
	}

	@Override
	public EmpresaEntity buscarXId(Long idEmpresa) {
		return empresaRepository.findById(idEmpresa).orElse(null);
	}

	@Override
	public EmpresaEntity actualizarEmpresa(EmpresaEntity empresa) {
		return empresaRepository.save(empresa);
	}

}
