package com.intermediary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intermediary.entity.RegistroEntity;

@Repository("RegistroRepository")
public interface RegistroRepository extends JpaRepository<RegistroEntity, Long>{

}
