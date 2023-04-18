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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.intermediary.entity.comun.AbstractEntidadComun;

@Entity
@Table(name = "negocio")
public class NegocioEntity extends AbstractEntidadComun{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5647669296142865701L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "contra_oferta_comprador")
	private String contraOfertaComprador;
	
	@Column(name = "contra_oferta_vendedor")
	private String contraOfertaVendedor;
	
	private int cantidad;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "producto")
	private ProductoEntity producto;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "comprador")
	private EmpresaEntity comprador;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vendedor")
	private EmpresaEntity vendedor;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "solicitud_compra")
	private SolicitudCompraEntity solicitudCompra;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContraOfertaComprador() {
		return contraOfertaComprador;
	}

	public void setContraOfertaComprador(String contraOfertaComprador) {
		this.contraOfertaComprador = contraOfertaComprador;
	}

	public String getContraOfertaVendedor() {
		return contraOfertaVendedor;
	}

	public void setContraOfertaVendedor(String contraOfertaVendedor) {
		this.contraOfertaVendedor = contraOfertaVendedor;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public ProductoEntity getProducto() {
		return producto;
	}

	public void setProducto(ProductoEntity producto) {
		this.producto = producto;
	}

	public EmpresaEntity getComprador() {
		return comprador;
	}

	public void setComprador(EmpresaEntity comprador) {
		this.comprador = comprador;
	}

	public EmpresaEntity getVendedor() {
		return vendedor;
	}

	public void setVendedor(EmpresaEntity vendedor) {
		this.vendedor = vendedor;
	}

	public SolicitudCompraEntity getSolicitudCompra() {
		return solicitudCompra;
	}

	public void setSolicitudCompra(SolicitudCompraEntity solicitudCompra) {
		this.solicitudCompra = solicitudCompra;
	}
}
