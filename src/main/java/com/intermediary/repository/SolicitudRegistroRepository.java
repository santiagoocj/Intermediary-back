package com.intermediary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intermediary.entity.SolicitudRegistroEntity;

@Repository("SolicitudRegistroRepository")
public interface SolicitudRegistroRepository extends JpaRepository<SolicitudRegistroEntity, Long>{

}
