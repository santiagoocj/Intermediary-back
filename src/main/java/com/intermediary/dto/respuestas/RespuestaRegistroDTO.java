package com.intermediary.dto.respuestas;

import com.intermediary.dto.RegistroDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RespuestaRegistroDTO {
	private RegistroDTO registro;
	private String mensaje;
}
