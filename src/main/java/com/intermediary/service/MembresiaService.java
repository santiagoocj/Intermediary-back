package com.intermediary.service;

import java.util.List;

import com.intermediary.entity.MembresiaEntity;

public interface MembresiaService {
	
	public MembresiaEntity buscarXId(Long id);
	
	public MembresiaEntity obtenerMembresiaBasica();
	
	public List<MembresiaEntity> listarMembresias();

}
