package com.tfg.entity;



import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class Producto {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty(message= "El campo nombre no puede ser nulo") @Size(min = 3, max = 40, message = "Nombre debe tener entre 3 y 40 caracteres")
	private String nombre;
	
	@NotNull( message= "El campo cantidad no puede ser nulo")
	@Min( value = 0, message = "El campo cantidad debe ser mayor o igual a 0" )
	private int cantidad;
	
	//@NotNull( message= "El campo precio no puede ser nulo")
	//@NotEmpty(message= "El campo precio no puede ser nulo")
	@Min( value = 0, message = "El campo precio debe ser mayor o igual a 0" )
	private float precio;

	@Lob
	private Byte[] image;
	
	@NotNull(message= "El campo oferta no puede ser nulo")
	private boolean oferta;
	
	@OneToMany( fetch = FetchType.EAGER, cascade = CascadeType.MERGE  )
	private List<Historico_precios> historico_precios;
	
	@NotNull(message= "El campo nuestros_productos no puede ser nulo")
	private boolean nuestros_productos;
	
	private float valoracion = 0;
	
	private long numero_valoraciones = 0;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Byte[] getImage() {
		return image;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}

	public boolean isOferta() {
		return oferta;
	}

	public void setOferta(boolean oferta) {
		this.oferta = oferta;
	}

	public boolean isNuestros_productos() {
		return nuestros_productos;
	}

	public void setNuestros_productos(boolean nuestros_productos) {
		this.nuestros_productos = nuestros_productos;
	}

	public List<Historico_precios> getHistorico_precios() {
		return historico_precios;
	}

	public void setHistorico_precios(List<Historico_precios> historico_precios) {
		this.historico_precios = historico_precios;
	}

	public float getValoracion() {
		return valoracion;
	}

	public void setValoracion(float valoracion) {
		this.valoracion = valoracion;
	}

	public long getNumero_valoraciones() {
		return numero_valoraciones;
	}

	public void setNumero_valoraciones(long numero_valoraciones) {
		this.numero_valoraciones = numero_valoraciones;
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + Arrays.hashCode(image);
	    result = prime * result
		    + Objects.hash(cantidad, id, nombre, nuestros_productos, oferta, precio);
	    return result;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
		return true;
	    if (obj == null)
		return false;
	    if (getClass() != obj.getClass())
		return false;
	    Producto other = (Producto) obj;
	    return cantidad == other.cantidad  && Arrays.equals(image, other.image)
		    && Objects.equals(nombre, other.nombre) && nuestros_productos == other.nuestros_productos
		    && oferta == other.oferta && Float.floatToIntBits(precio) == Float.floatToIntBits(other.precio);
	}
	
	
}
