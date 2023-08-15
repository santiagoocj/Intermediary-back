package com.intermediary.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresaDTO extends UsuarioDTO{
	private Long id;
	private String nit;
	private String nombre;
	private String razonSocial;
	private String codigoCiu;
	private String actividadPrincipal;
	private String tipoPersona;
	private String anexo;
	private String celular;
	private String correo;
	private RepresentanteLegalDTO representanteLegalDTO;
	private VigenciaDTO vigenciaDTO;
}
