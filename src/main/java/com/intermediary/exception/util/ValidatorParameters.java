package com.intermediary.exception.util;

import org.springframework.http.HttpStatus;

import com.intermediary.exception.BusinessExecption;

public class ValidatorParameters {
	private static final CharSequence CARACTER_ARROBA = "@";
	private static final int TAMANO_MAXIMO_NIT = 7;
	private static final int TAMANO_MINIMO_NIT = 4;
	private static final int TAMANO_MAXIMO_NOMBRE_EMPRESA = 20;
	
	public static void validarNitNulo(String nit, String mensaje) throws BusinessExecption{
		if(nit == null) {
			throw new BusinessExecption(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	public static void validarFormatoNit(String nit, String mensaje) throws BusinessExecption{
		if(nit.matches("[a-zA-Z]*")) {
			throw new BusinessExecption(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	public static void validarTamanoMinimoNit(String nit, String mensaje) throws BusinessExecption{
		if(nit.length() < TAMANO_MINIMO_NIT) {
			throw new BusinessExecption(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	public static void validarTamanoNit(String nit, String mensaje) throws BusinessExecption{
		if(nit.length() > TAMANO_MAXIMO_NIT) {
			throw new BusinessExecption(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	public static void validarNombreNulo(String nombre, String mensaje) throws BusinessExecption{
		if(nombre == null) {
			throw new BusinessExecption(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	public static void validartamanoMaximoNombre(String nombre, String mensaje) throws BusinessExecption{
		if(nombre.length() > TAMANO_MAXIMO_NOMBRE_EMPRESA) {
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
	
	public static void validarContenidoNulo(String contenido, String mensaje) throws BusinessExecption{
		if(contenido == null) {
			throw new BusinessExecption(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	
}
