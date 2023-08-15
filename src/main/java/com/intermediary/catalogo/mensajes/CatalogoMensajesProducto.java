package com.intermediary.catalogo.mensajes;

public class CatalogoMensajesProducto {
	
	private CatalogoMensajesProducto() {
		throw new IllegalStateException();
	}

	public static final String NOMBRE_NULO = "El campo nombre no puede ser vacio";
	public static final String PRECIO_NULO = "El campo precio no puede ser vacio";
	public static final String PRECIO_UNIDAD_NULO = "El campo precio por unidad no puede ser vacio";
	public static final String PRODUCTO_REGISTRADO = "El producto se ha registrado de manera exitosa";
	public static final String INACTIVACION_EXITOSA = "El producto se ha eliminado correctamente";
	public static final String ERROR_ELIMINAR_PRODUCTO = "Ocurrió un error al eliminar el producto";
	public static final String ERROR_REGISTRAR_PRODUCTO = "Ocurrió un error al registrar el producto";
	public static final String PRODUCTO_NO_ENCONTRADO = "El producto no se encuentra registrado en la base de datos";
	public static final String ERROR_INTERNO_DEL_SERVIDOR = "Ocurrio un error interno en el servidor, pero es posible que el producto se haya actualizado de manera exitosa";
	public static final String PRODUCTO_MODIFICADO_EXITOSAMENTE = "El producto se ha modificado de manera exitosa";
	public static final String PRODUCTO_ACTIVADO = "El producto ha sido activado con éxito.";

}
