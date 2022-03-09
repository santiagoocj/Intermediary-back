package com.intermediary.enums;

/*
 * Clase encargada de almacenar los estados respectivos a la entidad 
 */
public enum EstadoEntidad {
	
	ACTIVO, INACTIVO;

	/*
	 *	Permite obtener el estado mediante una entrada String 
	 */
	public static EstadoEntidad obtenerEstado(String estadoString) {
		try {
			return EstadoEntidad.valueOf(estadoString);
		} catch (Exception e) {
			return INACTIVO;
		}
	}
}
