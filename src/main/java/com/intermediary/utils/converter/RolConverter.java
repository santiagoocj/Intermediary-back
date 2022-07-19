package com.intermediary.utils.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import com.intermediary.dto.RolDTO;
import com.intermediary.entity.RoleEntity;
import com.intermediary.utils.converter.validator.GenericValidator;

@Component
public class RolConverter {
	
	@Autowired
	private GenericValidator<RoleEntity> genericValidator;
	
	public RolDTO entityToModel(RoleEntity roleEntity) throws BindException {
		RolDTO rolDTO = null;
		if(roleEntity != null) {
			rolDTO = new RolDTO();
			rolDTO.setId(roleEntity.getId());
			rolDTO.setNombre(roleEntity.getNombre());
		}
		genericValidator.validate(roleEntity);
		return rolDTO;
	}
	
	public List<RolDTO> entityToModel(List<RoleEntity> roleEntities) throws BindException{
		List<RolDTO> rolDTOs = null;
		if(!roleEntities.isEmpty()) {
			rolDTOs = new ArrayList<>();
			for(RoleEntity roleEntity: roleEntities) {
				rolDTOs.add(entityToModel(roleEntity));
			}
		}
		return rolDTOs;
	}
	
	public RoleEntity modelToEntity(RolDTO rolDTO) throws BindException {
		RoleEntity roleEntity = null;
		if(rolDTO != null) {
			roleEntity = new RoleEntity();
			roleEntity.setId(rolDTO.getId());
			roleEntity.setNombre(rolDTO.getNombre());
		}
		genericValidator.validate(roleEntity);
		return roleEntity;
	}
	
	public List<RoleEntity> modelToEntities(List<RolDTO> rolDTOs) throws BindException{
		List<RoleEntity> roleEntities = null;
		if(!rolDTOs.isEmpty()) {
			roleEntities = new ArrayList<>();
			for(RolDTO rolDTO: rolDTOs) {
				roleEntities.add(modelToEntity(rolDTO));
			}
		}
		return roleEntities;
	}

}
