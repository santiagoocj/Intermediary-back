package com.intermediary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intermediary.entity.CategoriaEntity;

@Repository("CategoriaRepository")
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

}
