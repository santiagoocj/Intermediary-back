package com.intermediary.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SolicitudRegistroDTO {
	private Long id;
	private String nombre;
	private String estado;
	private RegistroDTO registroDTO;
}
