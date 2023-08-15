package com.intermediary.entity.comun;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.intermediary.enums.EstadoEntidad;

import lombok.Getter;
import lombok.Setter;

/*
 * Entidad genérica donde extienden todas las entities 
 */
@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntidadComun implements Serializable {

	/**
	 * Constante de serialización
	 */
	private static final long serialVersionUID = 8359167048639132288L;
	
	/*
	 * Versión de la entidad 
	 */
	@Column(name = "version_entidad")
	protected Long versionEntidad;
	
	/*
	 * Estado de la entidad
	 */
	@Column(name = "estado", length = 30)
	@Enumerated(EnumType.STRING)
	protected EstadoEntidad estado;
	
	/*
	 * Fecha que indica cuando fue creada la entidad
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at")
	protected Date createAt;
	
	/*
	 * Fecha que indica cuando fue actualizada la entidad
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_at")
	protected Date updateAt;
	
	protected AbstractEntidadComun() {
		this.estado = EstadoEntidad.ACTIVO;
		this.versionEntidad = 0L;
	}
	
	/*
	 * Método que se ejecuta justo antes de persistir y asigna la fecha actual a la fecha de creación
	 */
	@PrePersist
	public void prePersist() {
		if(createAt == null) {
			this.createAt = new Date();
		}
	}
	
	/*
	 * Método que se ejecuta justo antes de actualizar la entidad y asigna la fecha actual a la fecha de actualización
	 */
	@PreUpdate
	public void preUpdate() {
		if(updateAt == null) {
			updateAt = new Date();
		}
	}

}
