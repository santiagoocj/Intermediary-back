package com.intermediary.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.intermediary.entity.SolicitudCompraEntity;

@Repository("SolicitudCompraRepository")
public interface SolicitudCompraRepository extends CrudRepository<SolicitudCompraEntity, Long>{

}
