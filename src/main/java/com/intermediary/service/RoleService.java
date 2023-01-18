package com.intermediary.service;

import java.util.List;

import com.intermediary.entity.EmpresaEntity;
import com.intermediary.entity.RoleEntity;

public interface RoleService {
	
	public RoleEntity obtenerRolPorNombre(String rol);
	
	public List<RoleEntity> actualizarEmpresaARolEmpresa(EmpresaEntity empresa);
	
	public List<RoleEntity> actualizarEmpresaARolEmpresaInicial(EmpresaEntity empresa);
}
