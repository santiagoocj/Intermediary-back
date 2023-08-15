package com.intermediary.catalogo.mensajes;

public class CatalogoMensajesUsuario {
	
	private CatalogoMensajesUsuario() {
		throw new IllegalStateException();
	}
	
	public static final String USUARIO_EXISTENTE_SISTEMA = "EL nombre de usuario ya existe en el sistema";
	public static final String ERROR_LOGIN_USUARIO_N_EXISTE= "Error en el login: no existe el usuario '{usuario}' en el sistema!";
}
