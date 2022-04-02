package com.intermediary.utils.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import com.intermediary.dto.ImagenProductoDTO;
import com.intermediary.entity.ImagenProductoEntity;
import com.intermediary.utils.converter.validator.GenericValidator;

@Component
public class ImagenProductoConverter {
	
	@Autowired
	private GenericValidator<ImagenProductoEntity> genericValidator;
	
	@Autowired
	private ProductoConverter productoConverter;
	
	public ImagenProductoDTO entityToModel (ImagenProductoEntity imagenProductoEntity) throws BindException {
		ImagenProductoDTO imagenProductoDTO = null;
		if(imagenProductoEntity != null) {
			imagenProductoDTO = new ImagenProductoDTO();
			imagenProductoDTO.setId(imagenProductoEntity.getId());
			imagenProductoDTO.setRuta(imagenProductoEntity.getRuta());
			imagenProductoDTO.setProducto(productoConverter.entityToModel(imagenProductoEntity.getProductoEntity()));
		}
		genericValidator.validate(imagenProductoEntity);
		return imagenProductoDTO;
	}
	
	public List<ImagenProductoDTO> entityToModel(List<ImagenProductoEntity> imagenProductoEntities) throws BindException{
		List<ImagenProductoDTO> imagenProductoDTOs = null;
		if(!imagenProductoEntities.isEmpty()) {
			imagenProductoDTOs = new ArrayList<>();
			for(ImagenProductoEntity imagenProductoEntity: imagenProductoEntities) {
				imagenProductoDTOs.add(entityToModel(imagenProductoEntity));
			}
		}
		return imagenProductoDTOs;
	}
	
	public ImagenProductoEntity modelToEntity(ImagenProductoDTO imagenProductoDTO) throws BindException{
		ImagenProductoEntity imagenProductoEntity = null;
		if(imagenProductoDTO != null) {
			imagenProductoEntity = new ImagenProductoEntity();
			imagenProductoEntity.setId(imagenProductoDTO.getId());
			imagenProductoEntity.setRuta(imagenProductoDTO.getRuta());
			imagenProductoEntity.setProductoEntity(productoConverter.modelToEntity(imagenProductoDTO.getProducto()));
		}
		genericValidator.validate(imagenProductoEntity);
		return imagenProductoEntity;
	}
	
	public List<ImagenProductoEntity> modelToEntity(List<ImagenProductoDTO> imagenProductoDTOs) throws BindException{
		List<ImagenProductoEntity> imagenProductoEntities = null;
		if(!imagenProductoDTOs.isEmpty()) {
			imagenProductoEntities = new ArrayList<>();
			for(ImagenProductoDTO imagenProductoDTO: imagenProductoDTOs) {
				imagenProductoEntities.add(modelToEntity(imagenProductoDTO));
			}
		}
		return imagenProductoEntities;
	}

}
