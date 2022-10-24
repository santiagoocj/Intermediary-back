package com.intermediary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intermediary.entity.ImagenProductoEntity;

@Repository("ImagenRepository")
public interface ImagenRepository extends JpaRepository<ImagenProductoEntity, Long>{

}
