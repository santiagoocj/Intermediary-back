package com.intermediary.dto.parametros;

import java.util.List;

import com.intermediary.dto.EmpresaDTO;
import lombok.Data;

@Data
public class RegistroEmpresaDTO {
	
	private EmpresaDTO empresa;
	private Long representanteLegal;
	private List<Long> categorias;

}
