package com.intermediary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.intermediary.entity.comun.AbstractEntidadComun;

@Entity
@Table(name = "solicitudes_de_registro")
public class SolicitudRegistroEntity extends AbstractEntidadComun{
	/**
	 * 
	 */
	private static final long serialVersionUID = 504434236468918980L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nombre;
	
	@Column(name = "estado_solicitud")
	private String estadoSolicitud;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_registro")
	private RegistroEntity registro;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstadoSolicitud() {
		return estadoSolicitud;
	}

	public void setEstadoSolicitud(String estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	public RegistroEntity getRegistro() {
		return registro;
	}

	public void setRegistro(RegistroEntity registro) {
		this.registro = registro;
	}
	
	
	

}
