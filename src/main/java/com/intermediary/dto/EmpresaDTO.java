package com.intermediary.dto;

import java.util.List;

import lombok.Data;

@Data
public class EmpresaDTO {
	private Long id;
	private String nit;
	private String razonSocial;
	private String codigoCiu;
	private String actividadPrincipal;
	private String tipoPersona;
	private String celular;
	private String Correo;
	private RepresentanteLegalDTO representanteLegalDTO;
	private List<CategoriaDTO> categoriaDTO;
}
