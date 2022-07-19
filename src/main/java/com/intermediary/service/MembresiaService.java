package com.intermediary.service;

import com.intermediary.entity.MembresiaEntity;

public interface MembresiaService {
	
	public MembresiaEntity buscarXId(Long id);
	
	public MembresiaEntity obtenerMembresiaBasica();

}
