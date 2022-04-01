package com.intermediary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intermediary.entity.ProductoEntity;

@Repository("ProductoRepository")
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {

}
