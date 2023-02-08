package com.intermediary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.intermediary.entity.comun.AbstractEntidadComun;
import com.intermediary.enums.EstadoNegociacion;


@Entity
@Table(name = "solicitud_de_compra")
public class SolicitudCompraEntity extends AbstractEntidadComun{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1678735926769641752L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "estado_negocio")
	private EstadoNegociacion estadoNegociacion;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EstadoNegociacion getEstadoNegociacion() {
		return estadoNegociacion;
	}

	public void setEstadoNegociacion(EstadoNegociacion estadoNegociacion) {
		this.estadoNegociacion = estadoNegociacion;
	}

}
