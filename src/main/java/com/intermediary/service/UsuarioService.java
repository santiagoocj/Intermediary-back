package com.intermediary.service;

import com.intermediary.dto.InfoBasicaUsuarioDTO;
import com.intermediary.entity.UsuarioEntity;

public interface UsuarioService {
	
	public UsuarioEntity findByUserName(String username); 
	
	public void validarInformacionUsuario(InfoBasicaUsuarioDTO infoUsuario);
	
	public String codificarContrasena(String password);

}
