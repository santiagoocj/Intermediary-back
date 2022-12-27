package com.intermediary.utils.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import com.intermediary.dto.VigenciaDTO;
import com.intermediary.entity.VigenciaEntity;
import com.intermediary.utils.converter.validator.GenericValidator;

@Component
public class VigenciaConverter {
	
	@Autowired
	private GenericValidator<VigenciaEntity> genericValidator;
	
	@Autowired
	private MembresiaConverter membresiaConverter;
	
	public VigenciaDTO entityToModel(VigenciaEntity vigenciaEntity) throws BindException {
		VigenciaDTO vigenciaDTO = null;
		if(vigenciaEntity != null) {
			vigenciaDTO = new VigenciaDTO();
			vigenciaDTO.setId(vigenciaEntity.getId());
			vigenciaDTO.setFechaVigencia(vigenciaEntity.getFechaVigencia());
			vigenciaDTO.setMembresiaDTO(membresiaConverter.EntityToModel(vigenciaEntity.getMembresia()));
			vigenciaDTO.setComprobantePago(vigenciaDTO.getComprobantePago());
		}
		genericValidator.validate(vigenciaEntity);
		return vigenciaDTO;
	}
	
	public VigenciaEntity modelToEntity(VigenciaDTO vigenciaDTO) throws BindException {
		VigenciaEntity vigenciaEntity = null;
		if(vigenciaDTO != null) {
			vigenciaEntity = new VigenciaEntity();
			vigenciaEntity.setId(vigenciaDTO.getId());
			vigenciaEntity.setFechaVigencia(vigenciaDTO.getFechaVigencia());
			vigenciaEntity.setMembresia(membresiaConverter.ModelToEntity(vigenciaDTO.getMembresiaDTO()));
			vigenciaEntity.setComprobantePago(vigenciaDTO.getComprobantePago());
		}
		genericValidator.validate(vigenciaEntity);
		return vigenciaEntity;
	}

}
