package com.intermediary.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.data.domain.Page;

import com.intermediary.catalogo.mensajes.CatalogoMensajesGenerales;
import com.intermediary.catalogo.mensajes.CatalogoMensajesProducto;
import com.intermediary.dto.ProductoDTO;
import com.intermediary.dto.respuestas.RespuestaProductoDTO;
import com.intermediary.entity.CategoriaEntity;
import com.intermediary.entity.EmpresaEntity;
import com.intermediary.entity.ImagenProductoEntity;
import com.intermediary.entity.ProductoEntity;
import com.intermediary.enums.EstadoEntidad;
import com.intermediary.repository.ProductoRepository;
import com.intermediary.service.ProductoService;
import com.intermediary.utils.converter.ProductoConverter;

import org.springframework.data.domain.Pageable;

@Service("ProductoService")
public class ProductoServiceImpl implements ProductoService{
	
	private static Logger logger = LogManager.getLogger(ProductoServiceImpl.class);
	private static final int NUMERO_PRODUCTOS_POR_PAGINA = 8;
	
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
	public RespuestaProductoDTO registrarProducto(ProductoDTO producto, Long idEmpresa, Long idCategoria) throws BindException {
		RespuestaProductoDTO respuesta = new RespuestaProductoDTO();
		CategoriaEntity categoriaSeleccionada = categoriaServiceImpl.buscarXId(idCategoria);
		EmpresaEntity empresa = empresaServiceImpl.buscarXId(idEmpresa);
		ProductoEntity productoGuardar = productoConverter.modelToEntity(producto);
		productoGuardar.setEstado(EstadoEntidad.INACTIVO);
		productoGuardar.setImagenes(new ArrayList<>());
		productoGuardar.setCategoria(categoriaSeleccionada);
		productoGuardar.setEmpresa(empresa);
		logger.info(() -> "Guardando producto " + producto.getNombre());
		productoRepository.save(productoGuardar);
		respuesta.setProducto(producto);
		respuesta.setMensaje(CatalogoMensajesProducto.PRODUCTO_REGISTRADO);
		return respuesta;
	}

	@Override
	public RespuestaProductoDTO inactivarProducto(Long idProducto) throws BindException {
		RespuestaProductoDTO respuesta = new RespuestaProductoDTO();
		ProductoEntity productoActual = productoRepository.findById(idProducto).orElse(null);
		if(productoActual != null) {
			productoActual.setEstado(EstadoEntidad.INACTIVO);
			productoRepository.save(productoActual);
			respuesta.setProducto(productoConverter.entityToModel(productoActual));
			respuesta.setMensaje(CatalogoMensajesProducto.INACTIVACION_EXITOSA);
		}else {
			respuesta.setMensaje(CatalogoMensajesProducto.ERROR_ELIMINAR_PRODUCTO);
		}
		return respuesta;
	}

	@Override
	public List<ProductoEntity> listarProductos(Long idEmpresa) {
		return productoRepository.findAllByIdEmpresa(idEmpresa);
	}

	@Override
	public ProductoDTO visualizarProducto(Long idProducto) throws BindException {
		ProductoEntity producto = productoRepository.findById(idProducto).orElse(null);
		return productoConverter.entityToModel(producto);
	}

	@Override
	public Page<List<ProductoEntity>> listarActivos(Integer page) {
		Pageable pageable = PageRequest.of(page, NUMERO_PRODUCTOS_POR_PAGINA);
		logger.info("Listando productos activos");
		return productoRepository.findByEstado(EstadoEntidad.ACTIVO, pageable);
	}

	@Override
	public RespuestaProductoDTO actualizarInformacionBasicaProducto(ProductoDTO producto) throws BindException {
		RespuestaProductoDTO respuesta = new RespuestaProductoDTO();
		ProductoEntity productoActualizar = productoRepository.findById(producto.getId()).orElse(null);
		if(productoActualizar == null) {
			respuesta.setMensaje(CatalogoMensajesProducto.PRODUCTO_NO_ENCONTRADO);
			return respuesta;
		}
		productoActualizar = actualizarInformacionBasica(productoActualizar, producto);
		productoRepository.save(productoActualizar);
		ProductoDTO productoRespuesta = productoConverter.entityToModel(productoActualizar);
		respuesta.setProducto(productoRespuesta);
		respuesta.setMensaje(CatalogoMensajesProducto.PRODUCTO_MODIFICADO_EXITOSAMENTE);
		return respuesta;
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
	public Map<String, Object> activarProducto(Long idEmpresa, Long idProducto) {
		Map<String, Object> respuesta = new HashMap<String, Object>();
		ProductoEntity producto = productoRepository.findById(idProducto).orElse(null);
		producto.setEstado(EstadoEntidad.ACTIVO);
		productoRepository.save(producto);
		respuesta.put(CatalogoMensajesGenerales.MENSAJE, CatalogoMensajesProducto.PRODUCTO_ACTIVADO);
		return respuesta;
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
