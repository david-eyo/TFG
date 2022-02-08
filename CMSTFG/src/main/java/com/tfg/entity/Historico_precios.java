package com.tfg.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Historico_precios {
    
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;    
	
	@NotNull( message= "El campo fechaIni no puede ser nulo")
	private LocalDateTime fechaIni;
	
	private LocalDateTime fechaFin;
	
	@OneToOne( fetch = FetchType.EAGER, cascade = CascadeType.PERSIST  )
	private Producto producto;

	public long getId() {
	    return id;
	}

	public void setId(long id) {
	    this.id = id;
	}

	public LocalDateTime getFechaIni() {
	    return fechaIni;
	}

	public void setFechaIni(LocalDateTime fechaIni) {
	    this.fechaIni = fechaIni;
	}

	public LocalDateTime getFechaFin() {
	    return fechaFin;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
	    this.fechaFin = fechaFin;
	}

	public Producto getProducto() {
	    return producto;
	}

	public void setProducto(Producto producto) {
	    this.producto = producto;
	}
	
	
	
}
