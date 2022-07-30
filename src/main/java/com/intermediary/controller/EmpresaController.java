package com.intermediary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intermediary.catalogo.mensajes.CatalogoMensajesEmpresa;
import com.intermediary.dto.EmpresaDTO;
import com.intermediary.dto.InfoBasicaUsuarioDTO;
import com.intermediary.dto.respuestas.RespuestaEmpresaDTO;
import com.intermediary.exception.BusinessExecption;
import com.intermediary.exception.util.ValidatorParameters;
import com.intermediary.service.impl.EmpresaServiceImpl;


@Controller
@RequestMapping("/api")
public class EmpresaController {
	
	@Autowired
	private EmpresaServiceImpl empresaService;

	@GetMapping("/empresas")
	public ResponseEntity<?> listarTodo(){
		return empresaService.encontrarTodos();
	}
	
	@PostMapping("/empresas/registro/{id-solicitud-registro}")
	public ResponseEntity<RespuestaEmpresaDTO> registrarEmpresa(@PathVariable(name = "id-solicitud-registro") Long idSolicitudRegistro, @RequestBody InfoBasicaUsuarioDTO infoRegistroUsuario) throws BusinessExecption{
		ValidatorParameters.validarIdNulo(idSolicitudRegistro, CatalogoMensajesEmpresa.URL_INCORRECTA);
		ValidatorParameters.validarNombreNulo(infoRegistroUsuario.getUserName(), CatalogoMensajesEmpresa.USER_NAME_NULO);
		ValidatorParameters.validarNombreNulo(infoRegistroUsuario.getPassword(), CatalogoMensajesEmpresa.PASSWORD_NULO);
		return empresaService.registroEmpresa(idSolicitudRegistro, infoRegistroUsuario);
	}
	
}
