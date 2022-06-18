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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.intermediary.entity.comun.AbstractEntidadComun;

@Entity
@Table(name = "empresas")
public class EmpresaEntity extends AbstractEntidadComun {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;

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
	
	@NotNull
	private String correo;

	private String celular;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "representante_legal")
	private RepresentanteLegalEntity representanteLegalEntity;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "membresia")
	private MembresiaEntity membresiaEntity;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vigencia")
	private VigenciaEntity vigenciaEntity;

	
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
	

	public MembresiaEntity getMembresiaEntity() {
		return membresiaEntity;
	}

	public void setMembresiaEntity(MembresiaEntity membresiaEntity) {
		this.membresiaEntity = membresiaEntity;
	}

	public VigenciaEntity getVigenciaEntity() {
		return vigenciaEntity;
	}

	public void setVigenciaEntity(VigenciaEntity vigenciaEntity) {
		this.vigenciaEntity = vigenciaEntity;
	}




	/**
	 * 
	 */
	private static final long serialVersionUID = -352920798242624085L;

}
