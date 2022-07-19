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
import com.intermediary.enums.EstadoSolicitudEnum;

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
	private EstadoSolicitudEnum estadoSolicitud;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_registro")
	private RegistroEntity registro;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_representante")
	private RepresentanteLegalEntity representanteLegal;

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

	public EstadoSolicitudEnum getEstadoSolicitud() {
		return estadoSolicitud;
	}

	public void setEstadoSolicitud(EstadoSolicitudEnum estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	public RegistroEntity getRegistro() {
		return registro;
	}

	public void setRegistro(RegistroEntity registro) {
		this.registro = registro;
	}

	public RepresentanteLegalEntity getRepresentanteLegal() {
		return representanteLegal;
	}

	public void setRepresentanteLegal(RepresentanteLegalEntity representanteLegal) {
		this.representanteLegal = representanteLegal;
	}
}
