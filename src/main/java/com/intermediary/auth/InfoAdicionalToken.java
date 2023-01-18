package com.intermediary.auth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.intermediary.entity.EmpresaEntity;
import com.intermediary.entity.RoleEntity;
import com.intermediary.entity.UsuarioEntity;
import com.intermediary.enums.RoleEnum;
import com.intermediary.service.UsuarioService;
import com.intermediary.service.impl.VigenciaServiceImpl;

@Component
public class InfoAdicionalToken implements TokenEnhancer{
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	@Qualifier("VigenciaService")
	private VigenciaServiceImpl vigenciaServiceImpl;
	

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		UsuarioEntity usuario = usuarioService.findByUserName(authentication.getName());
		validarMembresiaUsuario(usuario);
		Map<String, Object> info = new HashMap<>();
		info.put("nombre_usuario", usuario.getUserName());
		info.put("id_usuario", usuario.getId());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

	private void validarMembresiaUsuario(UsuarioEntity usuario) {
		if(usuario instanceof EmpresaEntity) {
			EmpresaEntity empresa = (EmpresaEntity) usuario;
			List<RoleEntity> rolEmpresa =empresa.getRoles();
			if(rolEmpresa.get(0).getNombre().equals(RoleEnum.EMPRESA.getRol())) {
				vigenciaServiceImpl.validarVigenciaMembresiaEmpresa(empresa);
			}
		}
		
	}

	
}
