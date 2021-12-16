package com.intermediary.dto;

import lombok.Data;

@Data
public class RepresentanteLegalDTO {
	private Long id;
	private String tipoDocumento;
	private String documento;
	private String nombre;
	private String apellidos;
	private String celular;
	private String email;
	
}
