package com.intermediary.entity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.intermediary.entity.comun.AbstractEntidadComun;

@Entity
@Table(name = "vigencias")
public class VigenciaEntity extends AbstractEntidadComun{

	/**
	 * 
	 */
	private static final long serialVersionUID = -35232064941813707L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fecha_vigencia")
	private LocalDate fechaVigencia;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "membresia")
	private MembresiaEntity membresia;
	
	@Column(name = "comprobante_pago")
	private String ComprobantePago;
	
	public VigenciaEntity(MembresiaEntity membresia) {
		this.fechaVigencia = calcularDiasDeVigencia(membresia.getDiasVigencia());
		this.membresia = membresia;
	}

	public VigenciaEntity() {
		
	}
	
	private LocalDate calcularDiasDeVigencia(int diasVigencia) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, diasVigencia);
		return LocalDate.ofInstant(calendar.toInstant(), ZoneId.systemDefault());
	}

	public Long getId() {
		return id;
	}

	public LocalDate getFechaVigencia() {
		return fechaVigencia;
	}

	public MembresiaEntity getMembresia() {
		return membresia;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFechaVigencia(LocalDate fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

	public void setMembresia(MembresiaEntity membresia) {
		this.membresia = membresia;
	}

	public String getComprobantePago() {
		return ComprobantePago;
	}

	public void setComprobantePago(String comprobantePago) {
		ComprobantePago = comprobantePago;
	}
	
}
