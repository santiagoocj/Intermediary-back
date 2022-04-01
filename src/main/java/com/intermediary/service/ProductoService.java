package com.intermediary.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;

import com.intermediary.dto.ProductoDTO;
import com.intermediary.dto.respuestas.RespuestaProductoDTO;
import com.intermediary.entity.ProductoEntity;

public interface ProductoService {
	
	public ResponseEntity<RespuestaProductoDTO> registrarProducto(ProductoDTO producto);
	
	public ResponseEntity<RespuestaProductoDTO> inactivarProducto(Long idProducto);
	
	public ResponseEntity<List<ProductoEntity>> listarProductos();
	
	public ResponseEntity<ProductoDTO> visualizarProducto(Long idProducto) throws BindException;
}
