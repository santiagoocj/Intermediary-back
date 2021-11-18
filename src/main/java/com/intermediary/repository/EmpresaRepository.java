package com.intermediary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intermediary.entity.EmpresaEntity;

@Repository("EmpresaRepository")
public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long>{

}
