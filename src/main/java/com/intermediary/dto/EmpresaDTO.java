package com.intermediary.dto;

import lombok.Data;

@Data
public class EmpresaDTO {
	private Long id;
	private String nit;
	private String nombre;
	private String razonSocial;
	private String codigoCiu;
	private String actividadPrincipal;
	private String tipoPersona;
	private String celular;
	private String Correo;
	private RepresentanteLegalDTO representanteLegalDTO;
	private MembresiaDTO membresiaDTO;
	private VigenciaDTO vigenciaDTO;
}
