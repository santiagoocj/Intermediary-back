package com.intermediary.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import com.intermediary.entity.ProductoEntity;
import com.intermediary.enums.EstadoEntidad;

@Repository("ProductoRepository")
public interface ProductoRepository extends PagingAndSortingRepository<ProductoEntity, Long> {
	
	Page<List<ProductoEntity>> findByEstado(EstadoEntidad estado, Pageable page);
	
	@Query(value = "SELECT * from productos WHERE productos.empresa = ?1", nativeQuery = true)
	List<ProductoEntity> findAllByIdEmpresa(Long idEmpresa);
	
	@Query(value = "SELECT * from productos p join categorias c ON p.categoria = c.id where p.estado = 'ACTIVO' and c.tipo_categoria = ?1", nativeQuery = true)
	Page<List<ProductoEntity>> findByCategoria(String categoria, Pageable page);

	@Query(value = "UPDATE productos p SET estado='INACTIVO' WHERE p.empresa = ?1", nativeQuery = true)
	void inactivarTodosLosProductos(Long id);

}
