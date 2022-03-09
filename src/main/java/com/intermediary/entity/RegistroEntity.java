package com.intermediary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.intermediary.entity.comun.AbstractEntidadComun;

@Entity
@Table(name = "registros")
public class RegistroEntity extends AbstractEntidadComun{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1589373471334614046L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nit;
	
	@Column(name = "nombre_empresa")
	private String nombreEmpresa;
	
	@Column(name = "razon_social")
	private String razonSocial;
	
	@Column(name = "codigo_ciu")
	private String codigoCiu;
	
	@Column(name = "actividad_principal")
	private String actividadPrincipal;
	
	@Column(name = "tipo_persona")
	private String tipoPersona;
	
	private String celular;
	
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
