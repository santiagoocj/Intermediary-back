package com.intermediary.dto.parametros;

import java.util.List;

import javax.validation.Valid;

import com.intermediary.entity.EmpresaEntity;

import lombok.Data;

@Data
public class RegistroEmpresaDTO {
	
	@Valid
	private EmpresaEntity empresa;
	private Long representanteLegal;
	private List<Long> categorias;

}
