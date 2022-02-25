package com.intermediary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intermediary.entity.MembresiaEntity;

@Repository("MembresiaRepository")
public interface MembresiaRepository extends JpaRepository<MembresiaEntity, Long>{

}
