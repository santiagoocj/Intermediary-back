package com.intermediary.enums;

public enum RoleEnum {
	
	ADMINISTRADOR("ROLE_ADMINISTRADOR"),
	EMPRESA_INICIAL("ROLE_EMPRESA_INICIAL"),
	EMPRESA("ROLE_EMPRESA");

	private final String rol;
	
	private RoleEnum(String rol) {
		this.rol = rol;
	}

	public String getRol() {
		return rol;
	}
}
