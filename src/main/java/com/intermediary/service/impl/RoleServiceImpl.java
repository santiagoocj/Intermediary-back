package com.intermediary.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.intermediary.entity.EmpresaEntity;
import com.intermediary.entity.RoleEntity;
import com.intermediary.enums.RoleEnum;
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

	@Override
	public List<RoleEntity> actualizarEmpresaARolEmpresa(EmpresaEntity empresa) {
		List<RoleEntity> roles = empresa.getRoles();
		roles.clear();
		roles.add(new RoleEntity(RoleEnum.EMPRESA.getId(), RoleEnum.EMPRESA.getRol()));
		return roles;
	}

	@Override
	public List<RoleEntity> actualizarEmpresaARolEmpresaInicial(EmpresaEntity empresa) {
		List<RoleEntity> roles = empresa.getRoles();
		roles.clear();
		roles.add(new RoleEntity(RoleEnum.EMPRESA_INICIAL.getId(), RoleEnum.EMPRESA_INICIAL.getRol()));
		return roles;
	}

}
