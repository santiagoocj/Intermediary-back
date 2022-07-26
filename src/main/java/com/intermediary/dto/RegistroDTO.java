package com.intermediary.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistroDTO {
	private Long id;
	private String nit;
	private String nombreEmpresa;
	private String razonSocial;
	private String codigoCiu;
	private String actividadPrincipal;
	private String anexo;
	private String tipoPersona;
	private String celular;
	private String email;
}
