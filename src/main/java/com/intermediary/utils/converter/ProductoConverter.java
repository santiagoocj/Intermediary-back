package com.intermediary.utils.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import com.intermediary.dto.ProductoDTO;
import com.intermediary.entity.ProductoEntity;
import com.intermediary.utils.converter.validator.GenericValidator;

@Component
public class ProductoConverter {
	
	@Autowired
	private GenericValidator<ProductoEntity> genericValidator;
	
	@Autowired
	private CategoriaConverter categoriaConverter;
	
	@Autowired
	private ImagenProductoConverter imagenProductoConverter;
	
	public ProductoDTO entityToModel(ProductoEntity productoEntity) throws BindException {
		ProductoDTO productoDTO = null;
		if(productoEntity != null) {
			productoDTO = new ProductoDTO();
			productoDTO.setId(productoEntity.getId());
			productoDTO.setNombre(productoEntity.getNombre());
			productoDTO.setDescripcion(productoEntity.getDescripcion());
			productoDTO.setDimensiones(productoEntity.getDimensiones());
			productoDTO.setPeso(productoEntity.getPeso());
			productoDTO.setPrecio(productoEntity.getPrecio());
			//productoDTO.setImagenes(imagenProductoConverter.entityToModel(productoEntity.getImagenes()));
			productoDTO.setPrecioUnidad(productoEntity.getPrecioUnidad());
			productoDTO.setCategoriaDTO(categoriaConverter.EntityToModel(productoEntity.getCategoria()));
		}
		genericValidator.validate(productoEntity);
		return productoDTO;
	}
	
	public ProductoEntity modelToEntity(ProductoDTO productoDTO) throws BindException {
		ProductoEntity productoEntity = null;
		if(productoDTO != null) {
			productoEntity = new ProductoEntity();
			productoEntity.setId(productoDTO.getId());
			productoEntity.setNombre(productoDTO.getNombre());
			productoEntity.setDescripcion(productoDTO.getDescripcion());
			productoEntity.setDimensiones(productoDTO.getDescripcion());
			productoEntity.setPeso(productoDTO.getPeso());
			productoEntity.setPrecio(productoDTO.getPrecio());
			//productoEntity.setImagenes(imagenProductoConverter.modelToEntity(productoDTO.getImagenes()));
			productoEntity.setPrecioUnidad(productoDTO.getPrecioUnidad());
			productoEntity.setCategoria(categoriaConverter.ModelToEntity(productoDTO.getCategoriaDTO()));
		}
		genericValidator.validate(productoEntity);
		return productoEntity;
	}

}
