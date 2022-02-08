package com.intermediary.utils.response;

public interface BasicError {

	/**
	 * Método para saber si ocurrió un error en el proceso que se estaba
	 * desarrollando
	 * 
	 * @return true si hubo un error, false si no hubo error
	 */
	boolean isError();

	/**
	 * Método que en caso de error, obtiene el mensaje de error o el nombre de la
	 * propiedad de un mensaje mostrar
	 * 
	 * @return mensaje de error o propiedad que indica el mensaje
	 */
	String getErrorMsg();
}
