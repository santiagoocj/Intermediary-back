package com.intermediary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intermediary.catalogo.mensajes.CatalogoMensajesProducto;
import com.intermediary.dto.ProductoDTO;
import com.intermediary.dto.respuestas.RespuestaProductoDTO;
import com.intermediary.entity.ProductoEntity;
import com.intermediary.exception.BusinessExecption;
import com.intermediary.exception.util.ValidatorParameters;
import com.intermediary.service.impl.ProductoServiceImpl;

@Controller
@RequestMapping("/api")
public class ProductoController {
	
	@Autowired
	private ProductoServiceImpl productoService;
	
	@PostMapping("/producto")
	public ResponseEntity<RespuestaProductoDTO> registrarProducto(@RequestBody ProductoDTO producto) throws BusinessExecption{
		ValidatorParameters.validarNombreNulo(producto.getNombre(), CatalogoMensajesProducto.NOMBRE_NULO);
		ValidatorParameters.validarNombreNulo(String.valueOf(producto.getPrecio()), CatalogoMensajesProducto.PRECIO_NULO);
		ValidatorParameters.validarNombreNulo(String.valueOf(producto.getPrecioUnidad()), CatalogoMensajesProducto.PRECIO_UNIDAD_NULO);
		return productoService.registrarProducto(producto);
	}
	
	@PostMapping("/producto/{idProducto}")
	public ResponseEntity<RespuestaProductoDTO> inactivarproducto(@PathVariable Long idProducto){
		return productoService.inactivarProducto(idProducto);
	}
	
	@GetMapping("/producto")
	public ResponseEntity<List<ProductoEntity>> listarProductos(){
		return productoService.listarProductos();
	}
	
	@GetMapping("/producto/{idProducto}")
	public ResponseEntity<ProductoDTO> visualizarProducto(@PathVariable Long idProducto) throws BindException{
		return productoService.visualizarProducto(idProducto);
	}

}
