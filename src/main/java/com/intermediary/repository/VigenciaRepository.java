package com.intermediary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.intermediary.dto.InformacionCompraMembresiaEmpresaDTO;
import com.intermediary.entity.VigenciaEntity;

@Repository("VigenciaRepository")
public interface VigenciaRepository extends JpaRepository<VigenciaEntity, Long>{

	@Query(value = "Select vi.id as idVigencia, em.usuario_id as idEmpresa, em.nombre as nombreEmpresa, em.celular, em.correo as email, vi.comprobante_pago as comprobantePago, mem.suscripcion as membresia "
			+ " from vigencias vi JOIN empresas em ON vi.id = em.vigencia "
			+ " JOIN membresias mem ON vi.membresia = mem.id "
			+ " where vi.estado = 'INACTIVO'", nativeQuery = true)
	List<InformacionCompraMembresiaEmpresaDTO> listarVigenciasInactivas();
}
