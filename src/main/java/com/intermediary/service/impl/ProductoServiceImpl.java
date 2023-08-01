package com.intermediary.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;

import com.intermediary.catalogo.mensajes.CatalogoMensajesGenerales;
import com.intermediary.catalogo.mensajes.CatalogoMensajesProducto;
import com.intermediary.dto.ProductoDTO;
import com.intermediary.dto.respuestas.RespuestaProductoDTO;
import com.intermediary.entity.CategoriaEntity;
import com.intermediary.entity.EmpresaEntity;
import com.intermediary.entity.ImagenProductoEntity;
import com.intermediary.entity.ProductoEntity;
import com.intermediary.enums.EstadoEntidad;
import com.intermediary.exception.DataException;
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
	
	@Autowired
	@Qualifier("CategoriaService")
	private CategoriaServiceImpl categoriaServiceImpl;
	
	@Autowired
	@Qualifier("EmpresaService")
	private EmpresaServiceImpl empresaServiceImpl;
	
	@Override
	public ResponseEntity<RespuestaProductoDTO> registrarProducto(ProductoDTO producto, Long idEmpresa, Long idCategoria) {
		RespuestaProductoDTO respuesta = new RespuestaProductoDTO();
		try {
			CategoriaEntity categoriaSeleccionada = categoriaServiceImpl.buscarXId(idCategoria);
			EmpresaEntity empresa = empresaServiceImpl.buscarXId(idEmpresa);
			ProductoEntity productoGuardar = productoConverter.modelToEntity(producto);
			productoGuardar.setEstado(EstadoEntidad.INACTIVO);
			productoGuardar.setImagenes(new ArrayList<>());
			productoGuardar.setCategoria(categoriaSeleccionada);
			productoGuardar.setEmpresa(empresa);
			productoRepository.save(productoGuardar);
			respuesta.setProducto(producto);
			respuesta.setMensaje(CatalogoMensajesProducto.PRODUCTO_REGISTRADO);
		} catch (BindException e) {
			throw new DataException(CatalogoMensajesProducto.ERROR_REGISTRAR_PRODUCTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<RespuestaProductoDTO>(respuesta, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RespuestaProductoDTO> inactivarProducto(Long idProducto) {
		RespuestaProductoDTO respuesta = new RespuestaProductoDTO();
		ProductoEntity productoActual = productoRepository.findById(idProducto).orElse(null);
		if(productoActual != null) {
			try {
				productoActual.setEstado(EstadoEntidad.INACTIVO);
				productoRepository.save(productoActual);
				respuesta.setProducto(productoConverter.entityToModel(productoActual));
				respuesta.setMensaje(CatalogoMensajesProducto.INACTIVACION_EXITOSA);
			} catch (BindException e) {
				throw new DataException(CatalogoMensajesProducto.ERROR_ELIMINAR_PRODUCTO, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			respuesta.setMensaje(CatalogoMensajesProducto.ERROR_ELIMINAR_PRODUCTO);
		}
		return new ResponseEntity<RespuestaProductoDTO>(respuesta, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<ProductoEntity>> listarProductos(Long idEmpresa) {
		return new ResponseEntity<List<ProductoEntity>>(productoRepository.findAllByIdEmpresa(idEmpresa), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ProductoDTO> visualizarProducto(Long idProducto) throws BindException {
		ProductoEntity producto = productoRepository.findById(idProducto).orElse(null);
		return new ResponseEntity<ProductoDTO>(productoConverter.entityToModel(producto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<ProductoEntity>> listarActivos() {
		List<ProductoEntity> productos = new ArrayList<>();
		productos = productoRepository.findByEstado(EstadoEntidad.ACTIVO);
		return new ResponseEntity<List<ProductoEntity>>(productos, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RespuestaProductoDTO> actualizarInformacionBasicaProducto(ProductoDTO producto) {
		RespuestaProductoDTO respuesta = new RespuestaProductoDTO();
		ProductoEntity productoActualizar = productoRepository.findById(producto.getId()).orElse(null);
		if(productoActualizar == null) {
			respuesta.setMensaje(CatalogoMensajesProducto.PRODUCTO_NO_ENCONTRADO);
			return new ResponseEntity<RespuestaProductoDTO>(respuesta, HttpStatus.NOT_FOUND);
		}
		productoActualizar = actualizarInformacionBasica(productoActualizar, producto);
		try {
			productoRepository.save(productoActualizar);
			ProductoDTO productoRespuesta = productoConverter.entityToModel(productoActualizar);
			respuesta.setProducto(productoRespuesta);
			respuesta.setMensaje(CatalogoMensajesProducto.PRODUCTO_MODIFICADO_EXITOSAMENTE);
		} catch (BindException e) {
			throw new DataException(CatalogoMensajesProducto.ERROR_INTERNO_DEL_SERVIDOR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<RespuestaProductoDTO>(respuesta, HttpStatus.OK);
	}
	
	private ProductoEntity actualizarInformacionBasica(ProductoEntity productoActualizar, ProductoDTO datosActualizar) {
		if(datosActualizar.getNombre() != null) {
			productoActualizar.setNombre(datosActualizar.getNombre());
		}
		if(datosActualizar.getDescripcion() != null) {
			productoActualizar.setDescripcion(datosActualizar.getDescripcion());
		}
		if(datosActualizar.getDimensiones() != null) {
			productoActualizar.setDimensiones(datosActualizar.getDimensiones());
		}
		if(productoActualizar.getPeso() != 0) {
			productoActualizar.setPeso(datosActualizar.getPeso());
		}
		if(productoActualizar.getPrecio() != 0) {
			productoActualizar.setPrecio(datosActualizar.getPrecio());
		}
		if(productoActualizar.getPrecioUnidad() != 0) {
			productoActualizar.setPrecioUnidad(datosActualizar.getPrecioUnidad());
		}
		return productoActualizar;
	}
	
	protected ProductoEntity obtenerProducto(Long idProducto) {
		return productoRepository.findById(idProducto).orElse(null);
	};
	
	protected void acutalizarProducto(ProductoEntity producto) {
		productoRepository.save(producto);
	}
	
	protected void asignarFoto(Long idProducto, ImagenProductoEntity imagen) {
		ProductoEntity producto = obtenerProducto(idProducto);
		producto.getImagenes().add(imagen);
	}

	@Override
	public List<ImagenProductoEntity> obtenerImagenesDelProducto(Long idProducto) {
		ProductoEntity producto = obtenerProducto(idProducto);
		return producto.getImagenes();
	}

	@Override
	public ResponseEntity<Map<String, Object>> activarProducto(Long idEmpresa, Long idProducto) {
		Map<String, Object> respuesta = new HashMap<String, Object>();
		ProductoEntity producto = productoRepository.findById(idProducto).orElse(null);
		producto.setEstado(EstadoEntidad.ACTIVO);
		productoRepository.save(producto);
		respuesta.put(CatalogoMensajesGenerales.MENSAJE, CatalogoMensajesProducto.PRODUCTO_ACTIVADO);
		return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.OK);
	}

	@Override
	public List<ProductoEntity> buscarXCategoria(String categoria) {
		return productoRepository.findByCategoria(categoria);
	}

	@Override
	public void inactivarTodosLosProductosDeEmpresa(EmpresaEntity empresa) {
		productoRepository.inactivarTodosLosProductos(empresa.getId());
	}

}
