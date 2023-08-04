package com.intermediary.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
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
	
	private static Logger logger = LogManager.getLogger(RegistroServiceImpl.class);
	
	@Autowired
	@Qualifier("RegistroRepository")
	private RegistroRepository registroRepository;
	
	@Autowired
	private RegistroConverter registroConverter;
	
	@Value("${ruta.base.anexo.documentos}")
	private String rutaBaseAnexo;

	@Override
	public RespuestaRegistroDTO realizarRegistro(RegistroDTO datosRegistro) throws BindException {
		validarNitDuplicado(datosRegistro);
		RespuestaRegistroDTO respuesta = null;
		RegistroEntity registroEntity = registroConverter.ModelToEntity(datosRegistro);
		Long idDatosRegistro = registroRepository.save(registroEntity).getId();
		logger.info("Registro realizado, id del registro: " + idDatosRegistro.toString());
		datosRegistro.setId(idDatosRegistro);
		respuesta = RespuestaRegistroDTO.builder().registro(datosRegistro).mensaje(CatalogoMensajesRegistro.REGISTRO_EXITOSO).build();
		return respuesta;
	}

	@Override
	public List<RegistroDTO> listarTodo() throws BindException {
		return registroConverter.EntityToModel(registroRepository.findAll());
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
				logger.info("Ruta documento para la empresa a registrar con id " + idEmpresa + ", " + rutaImagen.toString());
				empresaRegistrada.setAnexo(nombreAnexo);
				registroRepository.save(empresaRegistrada);
			} catch (Exception e) {
				logger.error("Error registrando documento " + e.getMessage());
				throw new DataException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}	
	}

	@Override
	public void validarNitDuplicado(RegistroDTO datosRegistro) {
		RegistroEntity registroEntity = registroRepository.findByNit(datosRegistro.getNit());
		if(registroEntity != null) {
			logger.info("NIT " + datosRegistro.getNit() + " ya registrado");
			throw new DataException(CatalogoMensajesRegistro.NIT_YA_REGISTRADO, HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
