package com.tfg.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Historico_precios {
    


	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//@NotNull( message= "El campo fechaIni no puede ser nulo")
	private LocalDateTime fechaIni;
	
	private LocalDateTime fechaFin;

	@ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.MERGE  )
	private Producto producto;

	@NotNull( message= "El campo precio no puede ser nulo")
	private float precio;

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

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public Historico_precios() {
	    super();
	}
	
	public Historico_precios(LocalDateTime fechaIni, Producto producto,
		    @NotNull(message = "El campo precio no puede ser nulo") float precio) {
		super();
		this.fechaIni = fechaIni;
		this.producto = producto;
		this.precio = precio;
	}

	@Override
	public int hashCode() {
	    return Objects.hash(fechaFin, fechaIni, precio, producto);
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
		return true;
	    if (obj == null)
		return false;
	    if (getClass() != obj.getClass())
		return false;
	    Historico_precios other = (Historico_precios) obj;
	    return Objects.equals(fechaFin, other.fechaFin) && Objects.equals(fechaIni, other.fechaIni)
		    && Float.floatToIntBits(precio) == Float.floatToIntBits(other.precio)
		    && Objects.equals(producto, other.producto);
	}
	
	


}
