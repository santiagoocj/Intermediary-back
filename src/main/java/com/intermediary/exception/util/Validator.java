package com.intermediary.exception.util;

import com.intermediary.exception.BusinessExecption;

public class Validator {
	private static final CharSequence CARACTER_ARROBA = "@";
	
	public static void validarNitNulo(String nit, String mensaje) throws BusinessExecption{
		if(nit == null) {
			throw new BusinessExecption(mensaje);
		}
	}
	public static void validarRazonSocialNulo(String razonSocial, String mensaje) throws BusinessExecption{
		if(razonSocial == null) {
			throw new BusinessExecption(mensaje);
		}
	}
	public static void validarCodigoCiuNulo(String codigoCiu, String mensaje) throws BusinessExecption{
		if(codigoCiu == null) {
			throw new BusinessExecption(mensaje);
		}
	}
	public static void validarActividadPrincipalNulo(String actividadPrincipal, String mensaje) throws BusinessExecption{
		if(actividadPrincipal == null) {
			throw new BusinessExecption(mensaje);
		}
	}
	public static void validarTipoPersonaNulo(String tipoPersona, String mensaje) throws BusinessExecption{
		if(tipoPersona == null) {
			throw new BusinessExecption(mensaje);
		}
	}
	public static void validarCelularNulo(String celular, String mensaje) throws BusinessExecption{
		if(celular == null) {
			throw new BusinessExecption(mensaje);
		}
	}
	public static void validarEmailNulo(String email, String mensaje) throws BusinessExecption{
		if(email == null) {
			throw new BusinessExecption(mensaje);
		}
	}
	public static void validarEmailPermitido(String email, String mensaje) throws BusinessExecption{
		if(!email.contains(CARACTER_ARROBA)) {
			throw new BusinessExecption(mensaje);
		}
	}
}
