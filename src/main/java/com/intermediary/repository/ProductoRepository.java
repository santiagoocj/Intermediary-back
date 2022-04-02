package com.intermediary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intermediary.entity.ProductoEntity;
import com.intermediary.enums.EstadoEntidad;

@Repository("ProductoRepository")
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
	
	List<ProductoEntity> findByEstado(EstadoEntidad estado);

}
