package com.intermediary.service;

import java.util.List;
import java.util.Map;

import org.springframework.validation.BindException;
import org.springframework.data.domain.Page;

import com.intermediary.dto.ProductoDTO;
import com.intermediary.dto.respuestas.RespuestaProductoDTO;
import com.intermediary.entity.EmpresaEntity;
import com.intermediary.entity.ImagenProductoEntity;
import com.intermediary.entity.ProductoEntity;

public interface ProductoService {
	
	public RespuestaProductoDTO registrarProducto(ProductoDTO producto, Long idEmpresa, Long idCategoria) throws BindException;
	
	public RespuestaProductoDTO inactivarProducto(Long idProducto) throws BindException;
	
	public List<ProductoEntity> listarProductos(Long idEmpresa);
	
	public Page<List<ProductoEntity>> listarActivos(Integer page);
	
	public ProductoDTO visualizarProducto(Long idProducto) throws BindException;
	
	public RespuestaProductoDTO actualizarInformacionBasicaProducto(ProductoDTO producto) throws BindException;
	
	public List<ImagenProductoEntity> obtenerImagenesDelProducto(Long idProducto);
	
	public Map<String, Object> activarProducto(Long idEmpresa, Long idProducto);
	
	public Page<List<ProductoEntity>> buscarXCategoria(String categoria, Integer page);
	
	public void inactivarTodosLosProductosDeEmpresa(EmpresaEntity empresa);
}
