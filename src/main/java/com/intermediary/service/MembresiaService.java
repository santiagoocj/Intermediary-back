package com.intermediary.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.intermediary.entity.MembresiaEntity;

public interface MembresiaService {
	
	public MembresiaEntity buscarXId(Long id);
	
	public MembresiaEntity obtenerMembresiaBasica();
	
	public List<MembresiaEntity> listarMembresias();
	
	public ResponseEntity<Map<String, Object>> actualizarMembresia(Long idEmpresa, Long idMembresia, MultipartFile comprobantePago);
	
	public ResponseEntity<Map<String, Object>> activarMembresia(Long idEmpresa, Long idVigencia);

}
