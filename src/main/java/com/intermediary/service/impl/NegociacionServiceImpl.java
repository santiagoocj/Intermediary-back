package com.intermediary.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
	public ResponseEntity<Map<String, Object>> crearNegociacion(Long idComprador, Long idProducto, NegocioDTO negocio) {
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
		response.put("mensaje", "se crea la negociación de manera exitosa, se procede a enviar la información al vendedor, por favor este pendiente del correo ya que de esta manera le informaremos cuando responda el vendedor");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public List<NegocioEntity> listarNegociosEmpresa(Long idEmpresa) {
		return negociacionRepository.listarNegociosEmpresa(idEmpresa).orElseThrow();
	}

	@Override
	public ResponseEntity<Map<String, Object>> cancelarNegociacion(Long idNegocio) {
		Map<String, Object> response = new HashMap<>();
		response.put("mensaje", "Se canceló la solicitud de manera exitosa");
		response.put("solicitud de compra", solicitudCompraServiceImpl.cancelarSolicitud(idNegocio));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Map<String, Object>> aceptarNegociacion(Long idNegocio, String contraOfertaVendedor) {
		Map<String, Object> response = new HashMap<>();
		if(contraOfertaVendedor == null) {
			contraOfertaVendedor = "Aceptada";
		}
		NegocioEntity negocio = negociacionRepository.findById(idNegocio).orElseThrow();
		negocio.setContraOfertaVendedor(contraOfertaVendedor);
		negocio.getSolicitudCompra().setEstadoNegociacion(EstadoNegociacion.ACEPTADA);
		negociacionRepository.save(negocio);
		response.put("mensaje", "La negociación se realizo de manera exitosa, pronto nos comunicameros con usted para efectuar la compra");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}

}
