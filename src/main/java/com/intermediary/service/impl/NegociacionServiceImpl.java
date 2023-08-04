package com.intermediary.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.intermediary.catalogo.mensajes.CatalogoMensajesGenerales;
import com.intermediary.catalogo.mensajes.CatalogoMensajesNegociacion;
import com.intermediary.dto.NegocioDTO;
import com.intermediary.entity.EmpresaEntity;
import com.intermediary.entity.NegocioEntity;
import com.intermediary.entity.ProductoEntity;
import com.intermediary.entity.SolicitudCompraEntity;
import com.intermediary.enums.EstadoNegociacion;
import com.intermediary.repository.NegociacionRepository;
import com.intermediary.service.NegociacionService;

@Service("NegociacionServiceImpl")
public class NegociacionServiceImpl implements NegociacionService{
	
	private static Logger logger = LogManager.getLogger(NegociacionServiceImpl.class);
	
	@Autowired
	@Qualifier("NegociacionRepository")
	private NegociacionRepository negociacionRepository;
	
	@Autowired
	@Qualifier("SolicitudCompraService")
	private SolicitudCompraServiceImpl solicitudCompraServiceImpl;
	
	@Autowired
	@Qualifier("ProductoService")
	private ProductoServiceImpl productoServiceImpl;
	
	@Autowired
	@Qualifier("EmpresaService")
	private EmpresaServiceImpl empresaServiceImpl;

	@Override
	public Map<String, Object> crearNegociacion(Long idComprador, Long idProducto, NegocioDTO negocio) {
		logger.info("inicio negociacion del producto con id " + idProducto);
		Map<String, Object> response = new HashMap<>();
		ProductoEntity producto = productoServiceImpl.obtenerProducto(idProducto);
		EmpresaEntity comprador = empresaServiceImpl.buscarXId(idComprador);
		EmpresaEntity vendedor = producto.getEmpresa();
		SolicitudCompraEntity solicitudCompra = solicitudCompraServiceImpl.crearSolicitudCompra();
		NegocioEntity negociacion = new NegocioEntity();
		negociacion.setCantidad(negocio.getCantidad());
		negociacion.setContraOfertaComprador(negocio.getContraOfertaComprador());
		negociacion.setProducto(producto);
		negociacion.setComprador(comprador);
		negociacion.setVendedor(vendedor);
		negociacion.setSolicitudCompra(solicitudCompra);
		negociacionRepository.save(negociacion);
		response.put(CatalogoMensajesGenerales.MENSAJE, CatalogoMensajesNegociacion.NEGOCIACION_CREADA);
		return response;
	}

	@Override
	public List<NegocioEntity> listarNegociosEmpresa(Long idEmpresa) {
		return negociacionRepository.listarNegociosEmpresa(idEmpresa).orElseThrow();
	}

	@Override
	public Map<String, Object> cancelarNegociacion(Long idNegocio) {
		Map<String, Object> response = new HashMap<>();
		response.put(CatalogoMensajesGenerales.MENSAJE, CatalogoMensajesNegociacion.CANCELAR_NEGOCIACION);
		response.put("solicitud de compra", solicitudCompraServiceImpl.cancelarSolicitud(idNegocio));
		logger.info("cancelar solicitud de compra para el negocio con id " + idNegocio);
		return response;
	}

	@Override
	public Map<String, Object> aceptarNegociacion(Long idNegocio, String contraOfertaVendedor) {
		Map<String, Object> response = new HashMap<>();
		if(contraOfertaVendedor == null) {
			contraOfertaVendedor = EstadoNegociacion.ACEPTADA.toString();
		}
		NegocioEntity negocio = negociacionRepository.findById(idNegocio).orElseThrow();
		negocio.setContraOfertaVendedor(contraOfertaVendedor);
		negocio.getSolicitudCompra().setEstadoNegociacion(EstadoNegociacion.ACEPTADA);
		negociacionRepository.save(negocio);
		response.put(CatalogoMensajesGenerales.MENSAJE, CatalogoMensajesNegociacion.NEGOCIACION_EXITOSA);
		logger.info("Negocio con id " + idNegocio + " aceptado");
		return response;
	}

}
