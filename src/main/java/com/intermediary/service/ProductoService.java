package com.intermediary.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;

import com.intermediary.dto.ProductoDTO;
import com.intermediary.dto.respuestas.RespuestaProductoDTO;
import com.intermediary.entity.ImagenProductoEntity;
import com.intermediary.entity.ProductoEntity;

public interface ProductoService {
	
	public ResponseEntity<RespuestaProductoDTO> registrarProducto(ProductoDTO producto, Long idEmpresa, Long idCategoria);
	
	public ResponseEntity<RespuestaProductoDTO> inactivarProducto(Long idProducto);
	
	public ResponseEntity<List<ProductoEntity>> listarProductos(Long idEmpresa);
	
	public ResponseEntity<List<ProductoEntity>> listarActivos();
	
	public ResponseEntity<ProductoDTO> visualizarProducto(Long idProducto) throws BindException;
	
	public ResponseEntity<RespuestaProductoDTO> actualizarInformacionBasicaProducto(ProductoDTO producto);
	
	public List<ImagenProductoEntity> obtenerImagenesDelProducto(Long idProducto);
	
	public ResponseEntity<Map<String, Object>> activarProducto(Long idEmpresa, Long idProducto);
	
	public List<ProductoEntity> buscarXCategoria(String categoria);
}
