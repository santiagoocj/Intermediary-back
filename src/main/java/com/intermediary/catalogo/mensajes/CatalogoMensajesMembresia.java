package com.intermediary.catalogo.mensajes;

public class CatalogoMensajesMembresia {
	
	private CatalogoMensajesMembresia() {
		throw new IllegalStateException();
	}
	
	public static final String ACTUALIZACION_MEMBRESIA_NOTIFICACION= "se actualizó la membresia de manera exitosa, se verificará el comprobante de pago y posteriormente se hará la activación.";
	public static final String ACTIVACION_MEMBRESIA_EXITOSA="se realizó la activación de la membresia de manera exitosa.";
	public static final String SOLICITUD_ACTIVACION_MEMBRESIA = "Solicitud de activación de Membresía";
}
