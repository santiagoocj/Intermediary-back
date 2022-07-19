package com.intermediary.dto;

import com.intermediary.enums.EstadoSolicitudEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CambiarEstadoSolicitudRegistroDTO {
	String nombreSolicitud;
	EstadoSolicitudEnum estado;
	String contenidoCorreoEstadoSolicitud;
}
