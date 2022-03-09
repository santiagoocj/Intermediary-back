package com.intermediary.exception.util;

import org.springframework.http.HttpStatus;

import com.intermediary.exception.BusinessExecption;

public class ValidatorParameters {
	private static final CharSequence CARACTER_ARROBA = "@";
	
	public static void validarNitNulo(String nit, String mensaje) throws BusinessExecption{
		if(nit == null) {
			throw new BusinessExecption(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	public static void validarNombreNulo(String nombre, String mensaje) throws BusinessExecption{
		if(nombre == null) {
			throw new BusinessExecption(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	public static void validarRazonSocialNulo(String razonSocial, String mensaje) throws BusinessExecption{
		if(razonSocial == null) {
			throw new BusinessExecption(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	public static void validarCodigoCiuNulo(String codigoCiu, String mensaje) throws BusinessExecption{
		if(codigoCiu == null) {
			throw new BusinessExecption(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	public static void validarActividadPrincipalNulo(String actividadPrincipal, String mensaje) throws BusinessExecption{
		if(actividadPrincipal == null) {
			throw new BusinessExecption(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	public static void validarTipoPersonaNulo(String tipoPersona, String mensaje) throws BusinessExecption{
		if(tipoPersona == null) {
			throw new BusinessExecption(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	public static void validarDocumentoNulo(String documento, String mensaje) throws BusinessExecption{
		if(documento == null) {
			throw new BusinessExecption(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	public static void validarCelularNulo(String celular, String mensaje) throws BusinessExecption{
		if(celular == null) {
			throw new BusinessExecption(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	public static void validarEmailNulo(String email, String mensaje) throws BusinessExecption{
		if(email == null) {
			throw new BusinessExecption(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	public static void validarEmailPermitido(String email, String mensaje) throws BusinessExecption{
		if(!email.contains(CARACTER_ARROBA)) {
			throw new BusinessExecption(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	public static void validarContenidoCorreoNulo(String contenidoCorreo, String mensaje) throws BusinessExecption{
		if(contenidoCorreo == null) {
			throw new BusinessExecption(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	public static void validarContenidoCorreoVacio(String contenidoCorreo, String mensaje) throws BusinessExecption{
		if(contenidoCorreo.isEmpty()) {
			throw new BusinessExecption(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	public static void validarIdNulo(Long id, String mensaje) throws BusinessExecption{
		if(id == null) {
			throw new BusinessExecption(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
}
