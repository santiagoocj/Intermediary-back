package com.intermediary.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name = "representante_legal")
public class RepresentanteLegalEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5764900898619889277L;

	@Id
	//clave primaria cedula?
	private Long id;
	
	/*¿y el documento donde se crea
	private String tipoDocumento;*/
	
	private String nombre;
	
	private String apellidos;
	
	private String contraseña;
	
	private String celular;
	
	@Email(message = "No es una dirección de correo bien formada")
	private String email;

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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
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
