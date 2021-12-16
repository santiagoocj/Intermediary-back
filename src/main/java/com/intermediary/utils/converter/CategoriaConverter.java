package com.intermediary.utils.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import com.intermediary.dto.CategoriaDTO;
import com.intermediary.entity.CategoriaEntity;
import com.intermediary.utils.converter.validator.GenericValidator;

@Component
public class CategoriaConverter {
	
	@Autowired
	private GenericValidator<CategoriaEntity> genericValidator;
	
	public List<CategoriaDTO> EntityToModel(List<CategoriaEntity> categoriasEntity) throws BindException{
		List<CategoriaDTO> categoriasDTO = null;
		if(!categoriasEntity.isEmpty()) {
			categoriasDTO = new ArrayList<>();
			for(CategoriaEntity categoriaEntity: categoriasEntity) {
				categoriasDTO.add(EntityToModel(categoriaEntity));
			}
		}
		return categoriasDTO;
	}
	
	public CategoriaDTO EntityToModel(CategoriaEntity categoriaEntity) throws BindException {
		CategoriaDTO categoriaDTO = null;
		if(categoriaEntity != null) {
			categoriaDTO = new CategoriaDTO();
			categoriaDTO.setId(categoriaEntity.getId());
			categoriaDTO.setCategoria(categoriaEntity.getCategoria());
		}
		genericValidator.validate(categoriaEntity);
		return categoriaDTO;
	}
	
	public List<CategoriaEntity> ModelToEntity(List<CategoriaDTO> categoriasDTOs) throws BindException{
		List<CategoriaEntity> categoriaEntities = null;
		if(!categoriasDTOs.isEmpty()) {
			categoriaEntities = new ArrayList<>();
			for(CategoriaDTO categoriaDTO: categoriasDTOs) {
				categoriaEntities.add(ModelToEntity(categoriaDTO));
			}
		}
		return categoriaEntities;
	}
	
	public CategoriaEntity ModelToEntity(CategoriaDTO categoriaDTO) throws BindException{
		CategoriaEntity categoriaEntity = null;
		if(categoriaDTO != null) {
			categoriaEntity = new CategoriaEntity();
			categoriaEntity.setId(categoriaDTO.getId());
			categoriaEntity.setCategoria(categoriaDTO.getCategoria());
		}
		genericValidator.validate(categoriaEntity);
		return categoriaEntity;
	}

}
