package com.intermediary.dto.respuestas;

import com.intermediary.dto.EmpresaDTO;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RespuestaEmpresaDTO {
	private EmpresaDTO empresa;
	private String mensaje;
}
