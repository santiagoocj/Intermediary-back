package com.intermediary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.intermediary.entity.ProductoEntity;
import com.intermediary.enums.EstadoEntidad;

@Repository("ProductoRepository")
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
	
	List<ProductoEntity> findByEstado(EstadoEntidad estado);
	
	@Query(value = "SELECT * from productos WHERE productos.empresa = ?1", nativeQuery = true)
	List<ProductoEntity> findAllByIdEmpresa(Long idEmpresa);
	
	@Query(value = "SELECT * from productos p join categorias c ON p.categoria = c.id where c.tipo_categoria = ?1", nativeQuery = true)
	List<ProductoEntity> findByCategoria(String categoria);

	@Query(value = "UPDATE productos p SET estado='INACTIVO' WHERE p.empresa = ?1", nativeQuery = true)
	void inactivarTodosLosProductos(Long id);

}
