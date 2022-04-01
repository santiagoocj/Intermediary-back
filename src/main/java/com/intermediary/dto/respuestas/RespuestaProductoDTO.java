package com.intermediary.dto.respuestas;

import com.intermediary.dto.ProductoDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaProductoDTO {
	private ProductoDTO producto;
	private String mensaje;
}
