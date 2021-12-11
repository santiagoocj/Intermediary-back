package com.intermediary.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "empresas")
public class EmpresaEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String nit;

	@Column(name = "razon_social")
	private String razonSocial;

	@Column(name = "codigo_ciu")
	private String codigoCiu;

	@NotNull
	@Column(name = "actividad_principal")
	private String actividadPrincipal;

	@Column(name = "tipo_persona")
	private String tipoPersona;
	
	@Email(message = "no es una dirección de correo bien formada")
	@NotNull
	private String correo;

	private String celular;
	
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "representante_legal")
	private RepresentanteLegalEntity representanteLegalEntity;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "categoria")
	private List<CategoriaEntity> categoriasEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getCodigoCiu() {
		return codigoCiu;
	}

	public void setCodigoCiu(String codigoCiu) {
		this.codigoCiu = codigoCiu;
	}

	public String getActividadPrincipal() {
		return actividadPrincipal;
	}

	public void setActividadPrincipal(String actividadPrincipal) {
		this.actividadPrincipal = actividadPrincipal;
	}

	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public RepresentanteLegalEntity getRepresentanteLegalEntity() {
		return representanteLegalEntity;
	}

	public void setRepresentanteLegalEntity(RepresentanteLegalEntity representanteLegalEntity) {
		this.representanteLegalEntity = representanteLegalEntity;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = -352920798242624085L;

}
