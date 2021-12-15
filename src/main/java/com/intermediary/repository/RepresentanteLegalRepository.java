package com.intermediary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intermediary.entity.RepresentanteLegalEntity;

@Repository("RepresentanteLegalRepository")
public interface RepresentanteLegalRepository extends JpaRepository<RepresentanteLegalEntity, Long>{

}
