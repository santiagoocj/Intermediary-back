package com.intermediary.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;

import com.intermediary.catalogo.mensajes.CatalogoMensajesProducto;
import com.intermediary.dto.ProductoDTO;
import com.intermediary.dto.respuestas.RespuestaProductoDTO;
import com.intermediary.entity.ProductoEntity;
import com.intermediary.enums.EstadoEntidad;
import com.intermediary.repository.ProductoRepository;
import com.intermediary.service.ProductoService;
import com.intermediary.utils.converter.ProductoConverter;

@Service("ProductoService")
public class ProductoServiceImpl implements ProductoService{
	
	@Autowired
	@Qualifier("ProductoRepository")
	private ProductoRepository productoRepository;
	
	@Autowired
	private ProductoConverter productoConverter;

	@Override
	public ResponseEntity<RespuestaProductoDTO> registrarProducto(ProductoDTO producto) {
		RespuestaProductoDTO respuesta = new RespuestaProductoDTO();
		try {
			ProductoEntity productoGuardar = productoConverter.modelToEntity(producto);
			productoRepository.save(productoGuardar);
			respuesta.setProducto(producto);
			respuesta.setMensaje(CatalogoMensajesProducto.PRODUCTO_REGISTRADO);
		} catch (BindException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<RespuestaProductoDTO>(respuesta, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RespuestaProductoDTO> inactivarProducto(Long idProducto) {
		RespuestaProductoDTO respuesta = new RespuestaProductoDTO();
		ProductoEntity productoActual = productoRepository.findById(idProducto).orElse(null);
		if(productoActual != null) {
			productoActual.setEstado(EstadoEntidad.INACTIVO);
			productoRepository.save(productoActual);
			try {
				respuesta.setProducto(productoConverter.entityToModel(productoActual));
				respuesta.setMensaje(CatalogoMensajesProducto.INACTIVACION_EXITOSA);
			} catch (BindException e) {
				e.printStackTrace();
			}
		}else {
			respuesta.setMensaje(CatalogoMensajesProducto.ERROR_ELIMINAR_PRODUCTO);
		}
		return new ResponseEntity<RespuestaProductoDTO>(respuesta, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<ProductoEntity>> listarProductos() {
		return new ResponseEntity<List<ProductoEntity>>(productoRepository.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ProductoDTO> visualizarProducto(Long idProducto) throws BindException {
		ProductoEntity producto = productoRepository.findById(idProducto).orElse(null);
		return new ResponseEntity<ProductoDTO>(productoConverter.entityToModel(producto), HttpStatus.OK);
	}

}
