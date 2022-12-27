package com.intermediary.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.intermediary.entity.EmpresaEntity;
import com.intermediary.entity.MembresiaEntity;
import com.intermediary.entity.RoleEntity;
import com.intermediary.entity.VigenciaEntity;
import com.intermediary.enums.EstadoEntidad;
import com.intermediary.enums.RoleEnum;
import com.intermediary.exception.DataException;
import com.intermediary.repository.MembresiaRepository;
import com.intermediary.service.MembresiaService;

@Service("MembresiaService")
public class MembresiaServiceImpl implements MembresiaService{
	
	@Autowired
	@Qualifier("MembresiaRepository")
	private MembresiaRepository membresiaRepository;

	@Autowired
	@Qualifier("VigenciaService")
	private VigenciaServiceImpl vigenciaServiceImpl;
	
	@Autowired
	@Qualifier("EmpresaService")
	private EmpresaServiceImpl empresaServiceImpl;
	
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
	public ResponseEntity<Map<String, Object>> actualizarMembresia(Long idEmpresa, Long idMembresia,
			MultipartFile comprobantePago) {
		try {
			MembresiaEntity membresia = membresiaRepository.findById(idMembresia).orElse(null);
			EmpresaEntity empresa = empresaServiceImpl.buscarXId(idEmpresa);
			VigenciaEntity vigencia = vigenciaServiceImpl.registroVigencia(membresia, comprobantePago);
			empresa.setVigenciaEntity(vigencia);
			empresaServiceImpl.actualizarEmpresa(empresa);
		} catch (Exception e) {
			throw new DataException("Error " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		Map<String, Object> respuesta = new HashMap<>();
		respuesta.put("mensaje", "se actualizó la membresia de manera exitosa, se verificará el comprobante de pago y posteriormente se hará la activación.");
		return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Map<String, Object>> activarMembresia(Long idEmpresa, Long idVigencia) {
		EmpresaEntity empresa;
		VigenciaEntity vigencia;
		try {
			empresa = actualizarRolEmpresa(idEmpresa);
			vigencia = actualizarVigencia(idVigencia);
		} catch (Exception e) {
			throw new DataException("Error " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		empresaServiceImpl.actualizarEmpresa(empresa);
		vigenciaServiceImpl.actualizarVigencia(vigencia);
		Map<String, Object> respuesta = new HashMap<>();
		respuesta.put("mensaje", "se realizó la activación de la membresia de manera exitosa.");
		return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.OK);
	}
	
	private EmpresaEntity actualizarRolEmpresa(Long idEmpresa) {
		EmpresaEntity empresa = empresaServiceImpl.buscarXId(idEmpresa);
		List<RoleEntity> roles = empresa.getRoles();
		roles.clear();
		//El rol empresa tiene el id 3
		roles.add(new RoleEntity(3L, RoleEnum.EMPRESA.toString()));

		empresa.setRoles(roles);
		return empresa;
	}
	
	private VigenciaEntity actualizarVigencia(Long idVigencia) {
		VigenciaEntity vigencia = vigenciaServiceImpl.buscarXId(idVigencia);
		vigencia.setEstado(EstadoEntidad.ACTIVO);
		return vigencia;
	}

}
