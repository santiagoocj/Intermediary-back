package com.intermediary.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CambiarEstadoSolicitudRegistroDTO {
	String nombreSolicitud;
	String estado;
	String contenidoCorreoEstadoSolicitud;
}
