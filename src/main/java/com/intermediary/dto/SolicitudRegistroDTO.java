package com.intermediary.dto;

import com.intermediary.enums.EstadoSolicitudEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SolicitudRegistroDTO {
	private Long id;
	private String nombre;
	private EstadoSolicitudEnum estado;
	private RegistroDTO registroDTO;
	private RepresentanteLegalDTO representanteLegalDTO;
}
