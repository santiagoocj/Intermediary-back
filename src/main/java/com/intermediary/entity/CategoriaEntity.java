package com.intermediary.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.intermediary.entity.comun.AbstractEntidadComun;

@Entity
@Table(name = "categorias")
public class CategoriaEntity extends AbstractEntidadComun{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7392790291821960112L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "tipo_categoria")
	private String categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
}
