package com.intermediary.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;

import com.intermediary.catalogo.mensajes.CatalogoMensajesGenerales;
import com.intermediary.catalogo.mensajes.CatalogoMensajesImagen;
import com.intermediary.entity.ImagenProductoEntity;
import com.intermediary.entity.ProductoEntity;
import com.intermediary.exception.DataException;
import com.intermediary.repository.ImagenRepository;
import com.intermediary.service.ImagenService;

@Service("ImagenServiceImpl")
public class ImagenServiceImpl implements ImagenService{
	
	@Value("${ruta.base.imagenes}")
	private String rutaBaseImagenes;
	
	@Autowired
	@Qualifier("ProductoService")
	private ProductoServiceImpl productoServiceImpl;
	
	@Autowired
	@Qualifier("ImagenRepository")
	private ImagenRepository imagenRepository;

	@Override
	public void subirImagen(MultipartFile imagen, Long idProducto, String empresa, String producto) throws BindException {
		String nombreImagen = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename().replace(" ", "");
		Path rutaImagen = Paths.get(crearDirectorioAlmacenarImagen(empresa, producto)).resolve(nombreImagen).toAbsolutePath();
		try {
			Files.copy(imagen.getInputStream(), rutaImagen);
			ImagenProductoEntity imagenProducto = crearEntidadImagen(idProducto, empresa+producto+"/"+nombreImagen);
			guardarImagen(imagenProducto);
			productoServiceImpl.asignarFoto(idProducto, imagenProducto);
		} catch (IOException e) {
			throw new DataException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	private String crearDirectorioAlmacenarImagen(String nombreDirectorio, String nombreProducto) {
		String ruta = rutaBaseImagenes + nombreDirectorio.concat(nombreProducto);
		File directorio = new File(ruta);
		if(!directorio.exists()) {
			directorio.mkdir();
		}
		return ruta;
	}
	
	private ImagenProductoEntity crearEntidadImagen(Long idProducto, String imagen) {
		ImagenProductoEntity imagenGuardar = new ImagenProductoEntity();
		imagenGuardar.setRuta(imagen);
		imagenGuardar.setProductoEntity(productoServiceImpl.obtenerProducto(idProducto));
		return imagenGuardar;
	}


	@Override
	public void guardarImagen(ImagenProductoEntity imagen) {
		imagenRepository.save(imagen);
	}


	@Override
	public ResponseEntity<Map<String, Object>> eliminarImagen(Long idProducto, int posicionImagenEnLista) {
		Map<String, Object> respuesta = new HashMap<String, Object>();
		ProductoEntity producto = productoServiceImpl.obtenerProducto(idProducto);
		ImagenProductoEntity imagen = producto.getImagenes().get(posicionImagenEnLista);
		eliminarFotoCarpeta(obtenerRutaFoto(imagen.getRuta()));
		actualizarImagenesEnElProducto(producto, posicionImagenEnLista);
		imagenRepository.delete(imagen);
		respuesta.put(CatalogoMensajesGenerales.MENSAJE, CatalogoMensajesImagen.IMAGEN_ELIMINADA);
		return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.OK);
	}
	
	private void actualizarImagenesEnElProducto(ProductoEntity producto, int posicionImagenEnLista) {
		producto.getImagenes().remove(posicionImagenEnLista);
		productoServiceImpl.acutalizarProducto(producto);
	}
	
	private Path obtenerRutaFoto(String ruta) {
		Path rutaFoto = Paths.get(rutaBaseImagenes).resolve(ruta).toAbsolutePath();
		return rutaFoto;
	}
	
	private void eliminarFotoCarpeta(Path rutaImagen) {
		File imagenEliminar = rutaImagen.toFile();
		if(imagenEliminar.exists() && imagenEliminar.canRead()) {
			imagenEliminar.delete();
		}
	}
}
