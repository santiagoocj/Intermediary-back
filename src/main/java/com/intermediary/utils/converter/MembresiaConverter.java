package com.intermediary.utils.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import com.intermediary.dto.MembresiaDTO;
import com.intermediary.entity.MembresiaEntity;
import com.intermediary.utils.converter.validator.GenericValidator;

@Component
public class MembresiaConverter {
	
	@Autowired
	private GenericValidator<MembresiaEntity> genericValidator;
	
	public MembresiaDTO EntityToModel(MembresiaEntity membresiaEntity) throws BindException {
		MembresiaDTO membresiaDTO = null;
		if(membresiaEntity != null) {
			membresiaDTO = new MembresiaDTO();
			membresiaDTO.setId(membresiaEntity.getId());
			membresiaDTO.setSuscripcion(membresiaEntity.getSuscripcion());
			membresiaDTO.setVigencia(membresiaEntity.getVigencia());
		}
		genericValidator.validate(membresiaEntity);
		return membresiaDTO;
	}
	
	public MembresiaEntity ModelToEntity(MembresiaDTO membresiaDTO) throws BindException {
		MembresiaEntity membresiaEntity = null;
		if(membresiaDTO != null) {
			membresiaEntity = new MembresiaEntity();
			membresiaEntity.setId(membresiaDTO.getId());
			membresiaEntity.setSuscripcion(membresiaDTO.getSuscripcion());
			membresiaEntity.setVigencia(membresiaDTO.getVigencia());
		}
		genericValidator.validate(membresiaEntity);
		return membresiaEntity;
	}
}
