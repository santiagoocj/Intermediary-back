package com.intermediary.utils.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import com.intermediary.dto.SolicitudRegistroDTO;
import com.intermediary.entity.SolicitudRegistroEntity;
import com.intermediary.utils.converter.validator.GenericValidator;

@Component
public class SolicitudRegistroConverter {
	
	@Autowired
	private GenericValidator<SolicitudRegistroEntity> genericValidator;
	
	@Autowired
	private RegistroConverter registroConverter;
	
	public List<SolicitudRegistroDTO> EntityToModel(List<SolicitudRegistroEntity> solicitudesRegistro) throws BindException{
		List<SolicitudRegistroDTO> solicitudesRespuesta = null;
		if(!solicitudesRegistro.isEmpty()) {
			solicitudesRespuesta = new ArrayList<>();
			for(SolicitudRegistroEntity solitudRegistroEntity: solicitudesRegistro) {
				solicitudesRespuesta.add(EntityToModel(solitudRegistroEntity));
			}
		}
		return solicitudesRespuesta;
	}
	
	public SolicitudRegistroDTO EntityToModel(SolicitudRegistroEntity solicitudRegistroEntity) throws BindException {
		SolicitudRegistroDTO solicitudRegistroDTO = null;
		if(solicitudRegistroEntity != null) {
			solicitudRegistroDTO = new SolicitudRegistroDTO();
			solicitudRegistroDTO.setId(solicitudRegistroEntity.getId());
			solicitudRegistroDTO.setNombre(solicitudRegistroEntity.getNombre());
			solicitudRegistroDTO.setEstado(solicitudRegistroEntity.getEstadoSolicitud());
			solicitudRegistroDTO.setRegistroDTO(registroConverter.EntityToModel(solicitudRegistroEntity.getRegistro()));
		}
		genericValidator.validate(solicitudRegistroEntity);
		return solicitudRegistroDTO;
	}
	
	
	public SolicitudRegistroEntity ModelToEntity(SolicitudRegistroDTO solicitudRegistroDTO) throws BindException {
		SolicitudRegistroEntity solicitudRegistroEntity = null;
		if(solicitudRegistroDTO != null) {
			solicitudRegistroEntity = new SolicitudRegistroEntity();
			solicitudRegistroEntity.setId(solicitudRegistroDTO.getId());
			solicitudRegistroEntity.setNombre(solicitudRegistroDTO.getNombre());
			solicitudRegistroEntity.setEstadoSolicitud(solicitudRegistroDTO.getEstado());
			solicitudRegistroEntity.setRegistro(registroConverter.ModelToEntity(solicitudRegistroDTO.getRegistroDTO()));
		}
		genericValidator.validate(solicitudRegistroEntity);
		return solicitudRegistroEntity;
	}

}
