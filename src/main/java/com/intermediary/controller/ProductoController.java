package com.intermediary.controller;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;

import com.intermediary.catalogo.mensajes.CatalogoMensajesProducto;
import com.intermediary.dto.ProductoDTO;
import com.intermediary.dto.respuestas.RespuestaProductoDTO;
import com.intermediary.entity.ImagenProductoEntity;
import com.intermediary.entity.ProductoEntity;
import com.intermediary.exception.BusinessExecption;
import com.intermediary.exception.DataException;
import com.intermediary.exception.util.ValidatorParameters;
import com.intermediary.service.impl.ProductoServiceImpl;

@RestController
@RequestMapping("/api")
public class ProductoController {
	
	private static Logger logger = LogManager.getLogger(ProductoController.class);
	
	@Autowired
	private ProductoServiceImpl productoService;
	
	@Secured({"ROLE_ADMINISTRADOR", "ROLE_EMPRESA", "ROLE_EMPRESA_INICIAL"})
	@PostMapping("/producto/registro/{idEmpresa}/{idCategoria}")
	public ResponseEntity<RespuestaProductoDTO> registrarProducto(@RequestBody ProductoDTO producto, @PathVariable Long idEmpresa, @PathVariable Long idCategoria) throws BusinessExecption{
		ValidatorParameters.validarNombreNulo(producto.getNombre(), CatalogoMensajesProducto.NOMBRE_NULO);
		ValidatorParameters.validarNombreNulo(String.valueOf(producto.getPrecio()), CatalogoMensajesProducto.PRECIO_NULO);
		ValidatorParameters.validarNombreNulo(String.valueOf(producto.getPrecioUnidad()), CatalogoMensajesProducto.PRECIO_UNIDAD_NULO);
		logger.info("validaciones iniciales pasadas al registrar producto");
		try {
			return new ResponseEntity<>(productoService.registrarProducto(producto, idEmpresa, idCategoria), HttpStatus.OK);
		} catch (BindException e) {
			logger.error(() -> "Error registrar producto. Error " + e.getMessage());
			throw new DataException(CatalogoMensajesProducto.ERROR_REGISTRAR_PRODUCTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Secured({"ROLE_ADMINISTRADOR", "ROLE_EMPRESA"})
	@PostMapping("/producto/{idProducto}")
	public ResponseEntity<RespuestaProductoDTO> inactivarproducto(@PathVariable Long idProducto){
		try {
			return new ResponseEntity<>(productoService.inactivarProducto(idProducto), HttpStatus.OK);
		} catch (BindException e) {
			logger.error(() -> "Error inactivar producto. Error " + e.getMessage());
			throw new DataException(CatalogoMensajesProducto.ERROR_ELIMINAR_PRODUCTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Secured({"ROLE_ADMINISTRADOR", "ROLE_EMPRESA", "ROLE_EMPRESA_INICIAL"})
	@PutMapping("/producto")
	public ResponseEntity<RespuestaProductoDTO> actualizarProducto(@RequestBody ProductoDTO producto){
		try {
			RespuestaProductoDTO respuestaProducto = productoService.actualizarInformacionBasicaProducto(producto);
			if(respuestaProducto.getProducto() == null) {
				return new ResponseEntity<>(respuestaProducto, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(respuestaProducto, HttpStatus.OK);
		} catch (BindException e) {
			logger.info(() -> "Error actualizar producto. Error " + e.getMessage());
			throw new DataException(CatalogoMensajesProducto.ERROR_INTERNO_DEL_SERVIDOR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Secured({"ROLE_ADMINISTRADOR", "ROLE_EMPRESA", "ROLE_EMPRESA_INICIAL"})
	@GetMapping("/producto/listar/{idEmpresa}")
	public ResponseEntity<List<ProductoEntity>> listarProductosXEmpresa(@PathVariable Long idEmpresa){
		return new ResponseEntity<>(productoService.listarProductos(idEmpresa), HttpStatus.OK);
	}
	
	@GetMapping("/producto/activo/page/{page}")
	public ResponseEntity<Page<List<ProductoEntity>>> listarTodosProductosActivos(@PathVariable Integer page){
		return new ResponseEntity<>(productoService.listarActivos(page), HttpStatus.OK);
	}
	
	@Secured("ROLE_EMPRESA")
	@PutMapping("/activar/producto/{idEmpresa}/{idProducto}")
	public ResponseEntity<Map<String, Object>> activarProductoPorEmpresa(@PathVariable Long idEmpresa, @PathVariable Long idProducto){
		logger.info(() -> "Inactivar producto con id " + idProducto);
		return new ResponseEntity<>(productoService.activarProducto(idEmpresa, idProducto), HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMINISTRADOR", "ROLE_EMPRESA", "ROLE_EMPRESA_INICIAL"})
	@GetMapping("/producto/{idProducto}")
	public ResponseEntity<ProductoDTO> visualizarProducto(@PathVariable Long idProducto) throws BindException{
		return new ResponseEntity<>(productoService.visualizarProducto(idProducto), HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMINISTRADOR", "ROLE_EMPRESA", "ROLE_EMPRESA_INICIAL"})
	@GetMapping("/producto/imagenes/visualizar/{idProducto}")
	public List<ImagenProductoEntity> obtenerImagenesProducto(@PathVariable Long idProducto) {
		return productoService.obtenerImagenesDelProducto(idProducto);
	}
	
	@GetMapping("/producto/categoria/{categoria}")
	public List<ProductoEntity> buscarXCategoria(@PathVariable String categoria){
		return productoService.buscarXCategoria(categoria);
	}
	
}
