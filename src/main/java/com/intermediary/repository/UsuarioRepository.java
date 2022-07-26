package com.intermediary.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.intermediary.entity.UsuarioEntity;

@Repository("UsuarioRepository")
public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Long>{
	
	public Optional<UsuarioEntity> findByUserName(String username);
}
