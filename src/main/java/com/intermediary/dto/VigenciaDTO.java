package com.intermediary.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VigenciaDTO {
	
	private Long id;
	private LocalDate fechaVigencia;
	private MembresiaDTO membresiaDTO;
	private String comprobantePago;
}
