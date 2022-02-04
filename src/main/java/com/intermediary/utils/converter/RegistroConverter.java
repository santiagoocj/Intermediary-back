package com.intermediary.utils.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import com.intermediary.dto.RegistroDTO;
import com.intermediary.entity.RegistroEntity;
import com.intermediary.utils.converter.validator.GenericValidator;

@Component
public class RegistroConverter {
	
	@Autowired
	private GenericValidator<RegistroEntity> genericValidator;
	
	public RegistroDTO EntityToModel(RegistroEntity registroEntity) throws BindException {
		RegistroDTO registroDTO = null;
		if(registroEntity != null) {
			registroDTO = new RegistroDTO();
			registroDTO.setId(registroEntity.getId());
			registroDTO.setNit(registroEntity.getNit());
			registroDTO.setRazonSocial(registroEntity.getRazonSocial());
			registroDTO.setCodigoCiu(registroEntity.getCodigoCiu());
			registroDTO.setActividadPrincipal(registroEntity.getActividadPrincipal());
			registroDTO.setTipoPersona(registroEntity.getTipoPersona());
			registroDTO.setCelular(registroEntity.getCelular());
			registroDTO.setEmail(registroEntity.getEmail());
		}
		genericValidator.validate(registroEntity);
		return registroDTO;
	}
	
	public RegistroEntity ModelToEntity(RegistroDTO registroDTO) throws BindException {
		RegistroEntity registroEntity = null;
		if(registroDTO != null) {
			registroEntity = new RegistroEntity();
			registroEntity.setId(registroDTO.getId());
			registroEntity.setNit(registroDTO.getNit());
			registroEntity.setRazonSocial(registroDTO.getRazonSocial());
			registroEntity.setCodigoCiu(registroDTO.getCodigoCiu());
			registroEntity.setActividadPrincipal(registroDTO.getActividadPrincipal());
			registroEntity.setTipoPersona(registroDTO.getTipoPersona());
			registroEntity.setCelular(registroDTO.getCelular());
			registroEntity.setEmail(registroDTO.getEmail());
		}
		genericValidator.validate(registroEntity);
		return registroEntity;
	}

}
