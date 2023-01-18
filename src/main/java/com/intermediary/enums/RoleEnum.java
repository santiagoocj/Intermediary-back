package com.intermediary.enums;

public enum RoleEnum {
	
	ADMINISTRADOR(2L,"ROLE_ADMINISTRADOR"),
	EMPRESA_INICIAL(1L,"ROLE_EMPRESA_INICIAL"),
	EMPRESA(3L,"ROLE_EMPRESA");

	private final Long id;
	private final String rol;
	
	private RoleEnum(Long id,String rol) {
		this.rol = rol;
		this.id = id;
	}

	public String getRol() {
		return rol;
	}

	public Long getId() {
		return id;
	}
	
}
