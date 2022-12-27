package com.intermediary.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.intermediary.dto.InformacionCompraMembresiaEmpresaDTO;
import com.intermediary.entity.MembresiaEntity;
import com.intermediary.entity.VigenciaEntity;

public interface VigenciaService {
	
	public VigenciaEntity registroVigencia(MembresiaEntity membresia, MultipartFile comprobantePago);
	
	public VigenciaEntity registroPrimeraVigencia();
	
	public List<InformacionCompraMembresiaEmpresaDTO> listarVigenciasInactivas();
	
	public VigenciaEntity buscarXId(Long idVigencia);
	
	public void actualizarVigencia(VigenciaEntity vigencia);

}
