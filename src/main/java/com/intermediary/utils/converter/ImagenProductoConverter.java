package com.intermediary.utils.converter;

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
	
	public ImagenProductoDTO EntityToModel (ImagenProductoEntity imagenProductoEntity) throws BindException {
		ImagenProductoDTO imagenProductoDTO = null;
		if(imagenProductoEntity != null) {
			imagenProductoDTO = new ImagenProductoDTO();
			imagenProductoDTO.setId(imagenProductoEntity.getId());
			imagenProductoDTO.setRuta(imagenProductoEntity.getRuta());
		}
		genericValidator.validate(imagenProductoEntity);
		return imagenProductoDTO;
	}

}
