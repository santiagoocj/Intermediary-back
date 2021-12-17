package com.intermediary.utils.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import com.intermediary.dto.RepresentanteLegalDTO;
import com.intermediary.entity.RepresentanteLegalEntity;
import com.intermediary.utils.converter.validator.GenericValidator;


@Component
public class RepresentanteLegalConverter {
	
	@Autowired
	private GenericValidator<RepresentanteLegalEntity> genericValidator;
	
	public RepresentanteLegalDTO EntityToModel(RepresentanteLegalEntity representanteLegalEntity) throws BindException {
		RepresentanteLegalDTO representanteLegalDTO = null;
		if(representanteLegalEntity != null) {
			representanteLegalDTO = new RepresentanteLegalDTO();
			representanteLegalDTO.setId(representanteLegalEntity.getId());
			representanteLegalDTO.setTipoDocumento(representanteLegalEntity.getTipoDocumento());
			representanteLegalDTO.setDocumento(representanteLegalEntity.getDocumento());
			representanteLegalDTO.setNombre(representanteLegalEntity.getNombre());
			representanteLegalDTO.setApellidos(representanteLegalEntity.getApellidos());
			representanteLegalDTO.setCelular(representanteLegalEntity.getCelular());
			representanteLegalDTO.setEmail(representanteLegalEntity.getEmail());
		}
		genericValidator.validate(representanteLegalEntity);
		return representanteLegalDTO;
		
	}
	
	public RepresentanteLegalEntity ModelToEntity(RepresentanteLegalDTO representanteLegalDTO) throws BindException {
		RepresentanteLegalEntity representanteLegalEntity = null;
		if(representanteLegalDTO != null) {
			representanteLegalEntity = new RepresentanteLegalEntity();
			representanteLegalEntity.setId(representanteLegalDTO.getId());
			representanteLegalEntity.setTipoDocumento(representanteLegalDTO.getTipoDocumento());
			representanteLegalEntity.setDocumento(representanteLegalDTO.getDocumento());
			representanteLegalEntity.setNombre(representanteLegalDTO.getNombre());
			representanteLegalEntity.setApellidos(representanteLegalDTO.getApellidos());
			representanteLegalEntity.setCelular(representanteLegalDTO.getCelular());
			representanteLegalEntity.setEmail(representanteLegalDTO.getEmail());
		}
		genericValidator.validate(representanteLegalEntity);
		return representanteLegalEntity;
	}

}
