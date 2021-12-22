package com.intermediary.utils.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import com.intermediary.dto.EmpresaCategoriaDTO;
import com.intermediary.entity.EmpresaCategoriaEntity;
import com.intermediary.utils.converter.validator.GenericValidator;

@Component
public class EmpresaCategoriaConverter {
	
	@Autowired
	private GenericValidator<EmpresaCategoriaEntity> emGenericValidator;
	
	@Autowired
	private CategoriaConverter categoriaConverter;
	
	public List<EmpresaCategoriaDTO> EntityToModel(List<EmpresaCategoriaEntity> empresasCategoriaEntity) throws BindException{
		List<EmpresaCategoriaDTO> empresasCategorias = null;
		if(!empresasCategoriaEntity.isEmpty()) {
			empresasCategorias = new ArrayList<>();
			for(EmpresaCategoriaEntity empresaCategoria: empresasCategoriaEntity) {
				empresasCategorias.add(EntityToModel(empresaCategoria));
			}
		}
		return empresasCategorias;
	}
	
	public EmpresaCategoriaDTO EntityToModel(EmpresaCategoriaEntity empresaCategoriaEntity) throws BindException {
		EmpresaCategoriaDTO empresaCategoriaDTO = null;
		if(empresaCategoriaEntity != null) {
			empresaCategoriaDTO = new EmpresaCategoriaDTO();
			empresaCategoriaDTO.setId(empresaCategoriaEntity.getId());
			empresaCategoriaDTO.setCategoriaDTO(categoriaConverter.EntityToModel(empresaCategoriaEntity.getCategoriaEntity()));
		}
		emGenericValidator.validate(empresaCategoriaEntity);
		return empresaCategoriaDTO;
	}
	
	
	public List<EmpresaCategoriaEntity> ModelToEntity(List<EmpresaCategoriaDTO> empresaCategoriaDTOs) throws BindException{
		List<EmpresaCategoriaEntity> empresaCategoriaEntities = null;
		if(!empresaCategoriaDTOs.isEmpty()) {
			empresaCategoriaEntities = new ArrayList<>();
			for(EmpresaCategoriaDTO empresaCategoria: empresaCategoriaDTOs) {
				empresaCategoriaEntities.add(ModelToEntity(empresaCategoria));
			}
		}
		return empresaCategoriaEntities;
	}
	
	public EmpresaCategoriaEntity ModelToEntity(EmpresaCategoriaDTO empresaCategoriaDTO) throws BindException {
		EmpresaCategoriaEntity empresaCategoriaEntity = null;
		if(empresaCategoriaDTO != null) {
			empresaCategoriaEntity = new EmpresaCategoriaEntity();
			empresaCategoriaEntity.setId(empresaCategoriaDTO.getId());
			empresaCategoriaEntity.setCategoriaEntity(categoriaConverter.ModelToEntity(empresaCategoriaDTO.getCategoriaDTO()));
		}
		emGenericValidator.validate(empresaCategoriaEntity);
		return empresaCategoriaEntity;
	}
}
