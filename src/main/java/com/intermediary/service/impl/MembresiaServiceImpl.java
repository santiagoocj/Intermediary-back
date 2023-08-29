package com.intermediary.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.intermediary.catalogo.mensajes.CatalogoMensajesGenerales;
import com.intermediary.catalogo.mensajes.CatalogoMensajesMembresia;
import com.intermediary.dto.EmailDTO;
import com.intermediary.entity.EmpresaEntity;
import com.intermediary.entity.MembresiaEntity;
import com.intermediary.entity.VigenciaEntity;
import com.intermediary.enums.EstadoEntidad;
import com.intermediary.repository.MembresiaRepository;
import com.intermediary.service.MembresiaService;

@Service("MembresiaService")
public class MembresiaServiceImpl implements MembresiaService{
	
	private static Logger logger = LogManager.getLogger(MembresiaServiceImpl.class);
	
	@Autowired
	@Qualifier("MembresiaRepository")
	private MembresiaRepository membresiaRepository;

	@Autowired
	@Qualifier("VigenciaService")
	private VigenciaServiceImpl vigenciaServiceImpl;
	
	@Autowired
	@Qualifier("EmpresaService")
	private EmpresaServiceImpl empresaServiceImpl;
	
	@Autowired
	@Qualifier("RoleService")
	private RoleServiceImpl roleServiceImpl;
	
	@Autowired(required = true)
	private EmailServiceImpl emailServiceImpl;
	
	@Override
	public MembresiaEntity buscarXId(Long id) {
		return membresiaRepository.findById(id).orElse(null);
	}

	@Override
	public MembresiaEntity obtenerMembresiaBasica() {
		return membresiaRepository.obtenerMembresiaBasica();
	}

	@Override
	public List<MembresiaEntity> listarMembresias() {
		return membresiaRepository.findAll();
	}

	@Override
	public Map<String, Object> actualizarMembresia(Long idEmpresa, Long idMembresia, MultipartFile comprobantePago) {
		MembresiaEntity membresia = membresiaRepository.findById(idMembresia).orElse(null);
		EmpresaEntity empresa = empresaServiceImpl.buscarXId(idEmpresa);
		VigenciaEntity vigencia = vigenciaServiceImpl.registroVigencia(membresia, comprobantePago);
		empresa.setVigenciaEntity(vigencia);
		empresaServiceImpl.actualizarEmpresa(empresa);
		Map<String, Object> respuesta = new HashMap<>();
		respuesta.put(CatalogoMensajesGenerales.MENSAJE, CatalogoMensajesMembresia.ACTUALIZACION_MEMBRESIA_NOTIFICACION);
		logger.info(() -> "Membresia actualizada para la empresa con id " + idEmpresa);
		return respuesta;
	}

	@Override
	public Map<String, Object> activarMembresia(Long idEmpresa, Long idVigencia) {
		EmpresaEntity empresa = actualizarRolEmpresa(idEmpresa);
		VigenciaEntity vigencia = actualizarVigencia(idVigencia);
		empresaServiceImpl.actualizarEmpresa(empresa);
		vigenciaServiceImpl.actualizarVigencia(vigencia);
		Map<String, Object> respuesta = new HashMap<>();
		EmailDTO email = emailServiceImpl.construirEmail(empresa.getCorreo(), CatalogoMensajesMembresia.SOLICITUD_ACTIVACION_MEMBRESIA, CatalogoMensajesMembresia.ACTIVACION_MEMBRESIA_EXITOSA);
		emailServiceImpl.sendEmail(email);
		respuesta.put(CatalogoMensajesGenerales.MENSAJE, CatalogoMensajesMembresia.ACTIVACION_MEMBRESIA_EXITOSA);
		return respuesta;
	}
	
	private EmpresaEntity actualizarRolEmpresa(Long idEmpresa) {
		EmpresaEntity empresa = empresaServiceImpl.buscarXId(idEmpresa);
		empresa.setRoles(roleServiceImpl.actualizarEmpresaARolEmpresa(empresa));
		logger.info(() -> "Actualizando permisos empresa con id " + idEmpresa);
		return empresa;
	}
	
	private VigenciaEntity actualizarVigencia(Long idVigencia) {
		VigenciaEntity vigencia = vigenciaServiceImpl.buscarXId(idVigencia);
		vigencia.setEstado(EstadoEntidad.ACTIVO);
		return vigencia;
	}

}
