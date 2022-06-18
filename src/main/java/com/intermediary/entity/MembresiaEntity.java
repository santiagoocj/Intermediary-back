package com.intermediary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.intermediary.entity.comun.AbstractEntidadComun;

@Entity
@Table(name = "membresias")
public class MembresiaEntity extends AbstractEntidadComun{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1974575278902510407L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String suscripcion;
	
	@Column(name = "dias_vigencia")
	private int diasVigencia;

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

	public int getDiasVigencia() {
		return diasVigencia;
	}

	public void setDiasVigencia(int diasVigencia) {
		this.diasVigencia = diasVigencia;
	}

}
