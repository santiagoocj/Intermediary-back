package com.intermediary.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;

import com.intermediary.catalogo.mensajes.CatalogoMensajesEmpresa;
import com.intermediary.dto.EmpresaDTO;
import com.intermediary.dto.respuestas.RespuestaEmpresaDTO;
import com.intermediary.entity.EmpresaEntity;
import com.intermediary.entity.MembresiaEntity;
import com.intermediary.entity.RegistroEntity;
import com.intermediary.entity.RepresentanteLegalEntity;
import com.intermediary.exception.DataException;
import com.intermediary.repository.EmpresaRepository;
import com.intermediary.repository.MembresiaRepository;
import com.intermediary.repository.RegistroRepository;
import com.intermediary.repository.RepresentanteLegalRepository;
import com.intermediary.service.EmpresaService;
import com.intermediary.utils.converter.EmpresaConverter;

@Service("EmpresaService")
public class EmpresaServiceImpl implements EmpresaService{

	@Autowired
	@Qualifier("EmpresaRepository")
	private EmpresaRepository empresaRepository;
	
	@Autowired
	@Qualifier("RepresentanteLegalRepository")
	private RepresentanteLegalRepository representanteLegalRepository;
	
	@Autowired
	@Qualifier("MembresiaRepository")
	private MembresiaRepository membresiaRepository;
	
	@Autowired
	@Qualifier("RegistroRepository")
	private RegistroRepository registroRepository;
	
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
			throw new DataException(CatalogoMensajesEmpresa.ERROR_CONSULTA_EMPRESAS, HttpStatus.NOT_FOUND);
		} catch (BindException e) {
			throw new DataException(CatalogoMensajesEmpresa.ERROR_SERVIDOR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(empresasDTOs == null || empresasDTOs.isEmpty()) {
			response.put(CatalogoMensajesEmpresa.MENSAJE, CatalogoMensajesEmpresa.SIN_EMPRESAS_EN_BASE_DATOS);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return  new ResponseEntity<List<EmpresaDTO>>(empresasDTOs, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<RespuestaEmpresaDTO> registroEmpresa(Long idRepresentante, Long idMembresia, Long idRegistro) {
		EmpresaEntity empresa = null;
		RepresentanteLegalEntity representante = null;
		MembresiaEntity membresia = null;
		RegistroEntity registro = null;
		RespuestaEmpresaDTO retorno = null;
		try {
			representante = representanteLegalRepository.findById(idRepresentante).orElse(null);
			membresia = membresiaRepository.findById(idMembresia).orElse(null);
			registro = registroRepository.findById(idRegistro).orElse(null);
			empresa = crearEmpresa(representante, membresia, registro);
			empresaRepository.save(empresa);
			retorno = new RespuestaEmpresaDTO();
			retorno.setEmpresa(converter.EntityToModel(empresa));
			retorno.setMensaje(CatalogoMensajesEmpresa.EMPRESA_CREADA_CON_EXITO);
		} catch (BindException e) {
			throw new DataException(CatalogoMensajesEmpresa.ERROR_INSERTAR_EMPRESAS, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		return new ResponseEntity<RespuestaEmpresaDTO>(retorno, HttpStatus.CREATED);
	}
	
	private EmpresaEntity crearEmpresa(RepresentanteLegalEntity representante, MembresiaEntity membresia, RegistroEntity registro) {
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
		empresa.setMembresiaEntity(membresia);
		return empresa;
	}

}
