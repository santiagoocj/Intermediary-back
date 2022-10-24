package com.intermediary.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;

import com.intermediary.entity.ImagenProductoEntity;

public interface ImagenService {
	
	public void subirImagen(MultipartFile imagen, Long idProducto, String empresa, String producto) throws BindException;
	
	public void guardarImagen(ImagenProductoEntity imagen);
	
	public ResponseEntity<Map<String, Object>> eliminarImagen(Long idProducto, int posicionImagenEnLista);
}
