package com.intermediary.dto.respuestas;

import com.intermediary.dto.RegistroDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaRegistroDTO {
	private RegistroDTO registro;
	private String mensaje;
}
