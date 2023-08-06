package com.intermediary.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.intermediary.dto.InformacionCompraMembresiaEmpresaDTO;
import com.intermediary.entity.EmpresaEntity;
import com.intermediary.entity.MembresiaEntity;
import com.intermediary.entity.VigenciaEntity;
import com.intermediary.enums.EstadoEntidad;
import com.intermediary.exception.DataException;
import com.intermediary.repository.VigenciaRepository;
import com.intermediary.service.VigenciaService;

@Service("VigenciaService")
public class VigenciaServiceImpl implements VigenciaService{
	
	private static Logger logger = LogManager.getLogger(VigenciaServiceImpl.class);
	
	@Autowired
	@Qualifier("VigenciaRepository")
	private VigenciaRepository vigenciaRepository;
	
	@Autowired
	@Qualifier("MembresiaService")
	private MembresiaServiceImpl membresiaService;
	
	@Autowired
	@Qualifier("RoleService")
	private RoleServiceImpl roleServiceImpl;
	
	@Autowired
	@Qualifier("ProductoService")
	private ProductoServiceImpl productoServiceImpl;
	
	@Autowired
	@Qualifier("EmpresaService")
	private EmpresaServiceImpl empresaServiceImpl;
	
	@Value("${ruta.base.comprobante.pago}")
	private String rutaBaseComprobantePago;

	@Override
	public VigenciaEntity registroVigencia(MembresiaEntity membresia, MultipartFile comprobantePago) {
		VigenciaEntity datosAGuardar = new VigenciaEntity(membresia);
		datosAGuardar.setEstado(EstadoEntidad.INACTIVO);
		if(comprobantePago != null) {
			String nombreImagen = UUID.randomUUID().toString() + "_" + comprobantePago.getOriginalFilename().replace(" ", "");
			Path rutaImagen = Paths.get(rutaBaseComprobantePago).resolve(nombreImagen).toAbsolutePath();
			try {
				Files.copy(comprobantePago.getInputStream(), rutaImagen);
				datosAGuardar.setComprobantePago(nombreImagen);
			} catch (Exception e) {
				logger.error("Error registrando vigencia. Error " + e.getMessage());
				throw new DataException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return vigenciaRepository.save(datosAGuardar);
	}

	@Override
	public VigenciaEntity registroPrimeraVigencia() {
		VigenciaEntity datosAGuardar = new VigenciaEntity(membresiaService.obtenerMembresiaBasica());
		return vigenciaRepository.save(datosAGuardar);
	}

	@Override
	public List<InformacionCompraMembresiaEmpresaDTO> listarVigenciasInactivas() {
		return vigenciaRepository.ListarVigenciasInactivas();
	}

	@Override
	public VigenciaEntity buscarXId(Long idVigencia) {
		return vigenciaRepository.findById(idVigencia).orElse(null);
	}

	@Override
	public void actualizarVigencia(VigenciaEntity vigencia) {
		vigenciaRepository.save(vigencia);
	}

	@Override
	public void validarVigenciaMembresiaEmpresa(EmpresaEntity empresa) {
		LocalDate fechaActual = LocalDate.now();
		if(fechaActual.isAfter(empresa.getVigenciaEntity().getFechaVigencia())) {
			logger.info("la fecha actual es mayor a la fecha de vencimiento");
			try {
				empresa.setVigenciaEntity(registroPrimeraVigencia());
				empresa.setRoles(roleServiceImpl.actualizarEmpresaARolEmpresaInicial(empresa));
				productoServiceImpl.inactivarTodosLosProductosDeEmpresa(empresa);
				empresaServiceImpl.actualizarEmpresa(empresa);
			} catch (Exception e) {
				logger.error("Error validando membresia. Error " + e.getMessage());
			}
		}
		
	}

}
