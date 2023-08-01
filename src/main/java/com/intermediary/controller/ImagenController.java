package com.intermediary.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.intermediary.catalogo.mensajes.CatalogoMensajesGenerales;
import com.intermediary.catalogo.mensajes.CatalogoMensajesImagen;
import com.intermediary.service.impl.ImagenServiceImpl;

@Controller
@RequestMapping("/api")
public class ImagenController {
	
	@Autowired
	private ImagenServiceImpl imagenServiceImpl;
	
	@Secured({"ROLE_ADMINISTRADOR", "ROLE_EMPRESA", "ROLE_EMPRESA_INICIAL"})
	@PostMapping("/imagen/upload")
	public ResponseEntity<Map<String, Object>> subirImagen(@RequestParam("imagen") List<MultipartFile> imagenes, @RequestParam("id") Long idProducto, @RequestParam("empresa") String empresa, @RequestParam("producto") String producto) throws BindException {
		Map<String, Object> respuesta = new HashMap<>();
		if(!imagenes.isEmpty()) {
			for (MultipartFile multipartFile : imagenes) {
				imagenServiceImpl.subirImagen(multipartFile, idProducto, empresa, producto);
			}
			respuesta.put(CatalogoMensajesGenerales.MENSAJE, CatalogoMensajesImagen.SUBIDA_IMAGEN_EXITOSA);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
		}
		respuesta.put(CatalogoMensajesGenerales.MENSAJE, CatalogoMensajesImagen.IMAGEN_NO_SELECCIONADA);
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
	}
	

	@Secured({"ROLE_ADMINISTRADOR", "ROLE_EMPRESA", "ROLE_EMPRESA_INICIAL"})
	@DeleteMapping("/imagen/{idProdcuto}/{posicionImagenEnLista}")
	public ResponseEntity<Map<String, Object>> eliminarImagen(@PathVariable Long idProdcuto, @PathVariable int posicionImagenEnLista){
		return imagenServiceImpl.eliminarImagen(idProdcuto, posicionImagenEnLista);
	}

}
