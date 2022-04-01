package com.intermediary.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProductoDTO {
	
	private Long id;
	private String nombre;
	private String descripcion;
	private String dimensiones;
	private double peso;
	private double precio;
	private List<ImagenProductoDTO> imagenes; 
	private double precioUnidad;
	private CategoriaDTO categoriaDTO;

}
