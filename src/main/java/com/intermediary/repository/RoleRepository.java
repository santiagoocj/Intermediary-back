package com.intermediary.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.intermediary.entity.RoleEntity;

@Repository("RoleRepository")
public interface RoleRepository extends CrudRepository<RoleEntity, Long>{
	
	public RoleEntity findByNombre(String rol);

}
