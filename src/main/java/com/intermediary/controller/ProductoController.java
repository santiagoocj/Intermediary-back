package com.intermediary.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.intermediary.catalogo.mensajes.CatalogoMensajesProducto;
import com.intermediary.dto.ProductoDTO;
import com.intermediary.dto.respuestas.RespuestaProductoDTO;
import com.intermediary.entity.ImagenProductoEntity;
import com.intermediary.entity.ProductoEntity;
import com.intermediary.exception.BusinessExecption;
import com.intermediary.exception.util.ValidatorParameters;
import com.intermediary.service.impl.ProductoServiceImpl;

@RestController
@RequestMapping("/api")
public class ProductoController {
	
	@Autowired
	private ProductoServiceImpl productoService;
	
	@Secured({"ROLE_ADMINISTRADOR", "ROLE_EMPRESA", "ROLE_EMPRESA_INICIAL"})
	@PostMapping("/producto/registro/{idEmpresa}/{idCategoria}")
	public ResponseEntity<RespuestaProductoDTO> registrarProducto(@RequestBody ProductoDTO producto, @PathVariable Long idEmpresa, @PathVariable Long idCategoria) throws BusinessExecption{
		ValidatorParameters.validarNombreNulo(producto.getNombre(), CatalogoMensajesProducto.NOMBRE_NULO);
		ValidatorParameters.validarNombreNulo(String.valueOf(producto.getPrecio()), CatalogoMensajesProducto.PRECIO_NULO);
		ValidatorParameters.validarNombreNulo(String.valueOf(producto.getPrecioUnidad()), CatalogoMensajesProducto.PRECIO_UNIDAD_NULO);
		return productoService.registrarProducto(producto, idEmpresa, idCategoria);
	}
	
	@Secured({"ROLE_ADMINISTRADOR", "ROLE_EMPRESA"})
	@PostMapping("/producto/{idProducto}")
	public ResponseEntity<RespuestaProductoDTO> inactivarproducto(@PathVariable Long idProducto){
		return productoService.inactivarProducto(idProducto);
	}
	
	@Secured({"ROLE_ADMINISTRADOR", "ROLE_EMPRESA", "ROLE_EMPRESA_INICIAL"})
	@PutMapping("/producto")
	public ResponseEntity<RespuestaProductoDTO> actualizarProducto(@RequestBody ProductoDTO producto){
		return productoService.actualizarInformacionBasicaProducto(producto);
	}
	
	@Secured({"ROLE_ADMINISTRADOR", "ROLE_EMPRESA", "ROLE_EMPRESA_INICIAL"})
	@GetMapping("/producto/listar/{idEmpresa}")
	public ResponseEntity<List<ProductoEntity>> listarProductosXEmpresa(@PathVariable Long idEmpresa){
		return productoService.listarProductos(idEmpresa);
	}
	
	@GetMapping("/producto/activo")
	public ResponseEntity<List<ProductoEntity>> listarTodosProductosActivos(){
		return productoService.listarActivos();
	}
	
	@Secured("ROLE_EMPRESA")
	@PutMapping("/activar/producto/{idEmpresa}/{idProducto}")
	public ResponseEntity<Map<String, Object>> activarProductoPorEmpresa(@PathVariable Long idEmpresa, @PathVariable Long idProducto){
		return productoService.activarProducto(idEmpresa, idProducto);
	}
	
	@Secured({"ROLE_ADMINISTRADOR", "ROLE_EMPRESA", "ROLE_EMPRESA_INICIAL"})
	@GetMapping("/producto/{idProducto}")
	public ResponseEntity<ProductoDTO> visualizarProducto(@PathVariable Long idProducto) throws BindException{
		return productoService.visualizarProducto(idProducto);
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
