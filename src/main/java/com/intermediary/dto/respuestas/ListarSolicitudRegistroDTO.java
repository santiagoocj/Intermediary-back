package com.intermediary.dto.respuestas;

import java.util.List;

import com.intermediary.dto.SolicitudRegistroDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ListarSolicitudRegistroDTO {
	private String mensaje;
	private List<SolicitudRegistroDTO> solicitudesRegistro;
}
