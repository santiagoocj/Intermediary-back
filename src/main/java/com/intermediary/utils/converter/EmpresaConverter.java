package com.intermediary.utils.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import com.intermediary.dto.EmpresaDTO;
import com.intermediary.entity.EmpresaEntity;
import com.intermediary.utils.converter.validator.GenericValidator;

@Component
public class EmpresaConverter {
	
	@Autowired
	private GenericValidator<EmpresaEntity> genericValidator;
	
	public List<EmpresaDTO> EntityToModel(List<EmpresaEntity> empresaEntity) throws BindException{
		List<EmpresaDTO> empresasDTO = null;
		if(!empresaEntity.isEmpty()) {
			empresasDTO = new ArrayList<>();
			for(EmpresaEntity empresa: empresaEntity) {
				empresasDTO.add(EntityToModel(empresa));
			}
		}
		return empresasDTO;
	}
	
	public EmpresaDTO EntityToModel(EmpresaEntity empresaEntity) throws BindException{
		EmpresaDTO empresaDTO = null;
		if(empresaEntity != null) {
			empresaDTO = new EmpresaDTO();
			empresaDTO.setId(empresaEntity.getId());
			empresaDTO.setNit(empresaEntity.getNit());
			empresaDTO.setRazonSocial(empresaEntity.getRazonSocial());
			empresaDTO.setCodigoCiu(empresaEntity.getCodigoCiu());
			empresaDTO.setActividadPrincipal(empresaEntity.getActividadPrincipal());
			empresaDTO.setTipoPersona(empresaEntity.getTipoPersona());
			empresaDTO.setCelular(empresaEntity.getCelular());
			empresaDTO.setCorreo(empresaDTO.getCorreo());
			//queda faltando RepresentanteLegalDTO y CategoriaDTO
		}
		genericValidator.validate(empresaEntity);
		return empresaDTO;
	}
	
	public EmpresaEntity modelToEntity(EmpresaDTO empresaDTO) throws BindException{
		EmpresaEntity empresaEntity = null;
		if(empresaDTO != null) {
			empresaEntity = new EmpresaEntity();
			empresaEntity.setId(empresaDTO.getId());
			empresaEntity.setNit(empresaDTO.getNit());
			empresaEntity.setRazonSocial(empresaDTO.getRazonSocial());
			empresaEntity.setCodigoCiu(empresaDTO.getCodigoCiu());
			empresaEntity.setActividadPrincipal(empresaDTO.getActividadPrincipal());
			empresaEntity.setTipoPersona(empresaDTO.getTipoPersona());
			empresaEntity.setCelular(empresaDTO.getCelular());
			empresaEntity.setCorreo(empresaDTO.getCorreo());
			//queda faltando RepresentanteLegalDTO y CategoriaDTO
		}
		genericValidator.validate(empresaEntity);
		return empresaEntity;
	}

}
