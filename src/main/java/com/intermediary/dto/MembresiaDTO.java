package com.intermediary.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MembresiaDTO {
	private Long id;
	private String suscripcion;
	private int diasVigencia;
	private BigDecimal valor;
}
