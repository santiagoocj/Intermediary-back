package com.intermediary.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intermediary.catalogo.mensajes.CatalogoMensajesUsuario;
import com.intermediary.dto.InfoBasicaUsuarioDTO;
import com.intermediary.entity.UsuarioEntity;
import com.intermediary.exception.DataException;
import com.intermediary.repository.UsuarioRepository;
import com.intermediary.service.UsuarioService;

@Service("UsuarioService")
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService{
	
	private Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioEntity usuario = usuarioRepository.findByUserName(username).orElse(null);
		
		if(usuario == null) {
			logger.error("Error en el login: no existe el usuario '{0}' en el sistema!", username);
			throw new UsernameNotFoundException(CatalogoMensajesUsuario.ERROR_LOGIN_USUARIO_N_EXISTE.replace("{usuario}", username));
		}
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.collect(Collectors.toList());
		return new User(usuario.getUserName(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}

	@Override
	@Transactional(readOnly = true)
	public UsuarioEntity findByUserName(String username) {
		return  usuarioRepository.findByUserName(username).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public void validarInformacionUsuario(InfoBasicaUsuarioDTO infoUsuario) {
		UsuarioEntity validacionesUsuario = findByUserName(infoUsuario.getUserName());
		if(validacionesUsuario != null) {
			throw new DataException(CatalogoMensajesUsuario.USUARIO_EXISTENTE_SISTEMA, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public String codificarContrasena(String password) {
		return passwordEncoder.encode(password);
	}

}
