package com.intermediary.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.intermediary.entity.RoleEntity;
import com.intermediary.repository.RoleRepository;
import com.intermediary.service.RoleService;

@Service("RoleService")
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	@Qualifier("RoleRepository")
	private RoleRepository roleRepository;

	@Override
	public RoleEntity obtenerRolPorNombre(String rol) {
		return roleRepository.findByNombre(rol);
	}

}
