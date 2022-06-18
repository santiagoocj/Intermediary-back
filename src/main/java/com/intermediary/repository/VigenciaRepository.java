package com.intermediary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intermediary.entity.VigenciaEntity;

@Repository("VigenciaRepository")
public interface VigenciaRepository extends JpaRepository<VigenciaEntity, Long>{

}
