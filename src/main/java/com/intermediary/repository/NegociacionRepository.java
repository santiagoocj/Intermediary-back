package com.intermediary.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.intermediary.entity.NegocioEntity;

@Repository("NegociacionRepository")
public interface NegociacionRepository extends JpaRepository<NegocioEntity, Long>{
	
	@Query(value = "Select * from negocio where negocio.comprador = ?1 or negocio.vendedor = ?1", nativeQuery = true)
	public Optional<List<NegocioEntity>> listarNegociosEmpresa(Long idEmpresa);

}
