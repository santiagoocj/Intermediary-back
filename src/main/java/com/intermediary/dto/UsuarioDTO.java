package com.intermediary.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
	private String userName;
	private String password;
	private Boolean enabled;
	private List<RolDTO> roles;
}
