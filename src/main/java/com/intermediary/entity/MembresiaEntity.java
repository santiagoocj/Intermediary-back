package com.intermediary.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "membresias")
public class MembresiaEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1974575278902510407L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String suscripcion;
	
	private String vigencia;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSuscripcion() {
		return suscripcion;
	}

	public void setSuscripcion(String suscripcion) {
		this.suscripcion = suscripcion;
	}

	public String getVigencia() {
		return vigencia;
	}

	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}
	

}
