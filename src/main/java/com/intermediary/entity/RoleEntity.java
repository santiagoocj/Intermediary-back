package com.intermediary.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "roles")
public class RoleEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1871150375884855658L;

	public RoleEntity(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	public RoleEntity() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String nombre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
