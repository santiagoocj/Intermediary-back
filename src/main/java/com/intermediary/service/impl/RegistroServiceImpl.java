package com.intermediary.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.intermediary.catalogo.mensajes.CatalogoMensajesRegistro;
import com.intermediary.dto.RegistroDTO;
import com.intermediary.dto.respuestas.RespuestaRegistroDTO;
import com.intermediary.entity.RegistroEntity;
import com.intermediary.exception.DataException;
import com.intermediary.repository.RegistroRepository;
import com.intermediary.service.RegistroService;
import com.intermediary.utils.converter.RegistroConverter;

@Service("RegistroService")
public class RegistroServiceImpl implements RegistroService{
	
	@Autowired
	@Qualifier("RegistroRepository")
	private RegistroRepository registroRepository;
	
	@Autowired
	private RegistroConverter registroConverter;
	
	@Value("${ruta.base.anexo.documentos}")
	private String rutaBaseAnexo;

	@Override
	public ResponseEntity<RespuestaRegistroDTO> realizarRegistro(RegistroDTO datosRegistro) {
		RespuestaRegistroDTO respuesta = null;
		try {
			RegistroEntity registroEntity = registroConverter.ModelToEntity(datosRegistro);
			Long idDatosRegistro = registroRepository.save(registroEntity).getId();
			datosRegistro.setId(idDatosRegistro);
			respuesta = RespuestaRegistroDTO.builder().registro(datosRegistro).mensaje(CatalogoMensajesRegistro.REGISTRO_EXITOSO).build();
		}catch (Exception e) {
			throw new DataException(CatalogoMensajesRegistro.REGISTRO_FALLIDO, HttpStatus.BAD_REQUEST);
		} 
		return new ResponseEntity<RespuestaRegistroDTO>(respuesta, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<RegistroDTO>> listarTodo() {
		List<RegistroDTO> registros;
		try {
			registros = registroConverter.EntityToModel(registroRepository.findAll());
		} catch (Exception e) {
			throw new DataException(CatalogoMensajesRegistro.ERROR_LISTAR_REGISTRO, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<RegistroDTO>>(registros, HttpStatus.OK);
	}

	@Override
	public RegistroEntity buscarXId(Long idRegistro) {
		RegistroEntity registro = registroRepository.findById(idRegistro).orElseThrow();
		return registro;
	}

	@Override
	public void registrarDocumento(MultipartFile documento, Long idEmpresa) {
		RegistroEntity empresaRegistrada = registroRepository.findById(idEmpresa).orElseThrow();
		if(documento != null) {
			String nombreAnexo = UUID.randomUUID().toString() + "_" + documento.getOriginalFilename().replace(" ", "");
			Path rutaImagen = Paths.get(rutaBaseAnexo).resolve(nombreAnexo).toAbsolutePath();
			try {
				Files.copy(documento.getInputStream(), rutaImagen);
				empresaRegistrada.setAnexo(nombreAnexo);
				registroRepository.save(empresaRegistrada);
			} catch (Exception e) {
				throw new DataException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
	}

}
