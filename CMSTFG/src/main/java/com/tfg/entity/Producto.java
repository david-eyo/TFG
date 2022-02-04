package com.tfg.entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	
	@NotNull( message= "El campo precio no puede ser nulo")
	//@NotEmpty(message= "El campo precio no puede ser nulo")
	@Min( value = 0, message = "El campo precio debe ser mayo o igual a 0" )
	private float precio;

	@Lob
	private Byte[] image;
	
	@NotNull(message= "El campo oferta no puede ser nulo")
	private boolean oferta = false;
	
	private int precio_anterior;
	
	//@NotEmpty(message= "El campo nuestros_productos no puede ser nulo")
	@NotNull(message= "El campo nuestros_productos no puede ser nulo")
	private boolean nuestros_productos = false;
	
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

	public int getPrecio_anterior() {
		return precio_anterior;
	}

	public void setPrecio_anterior(int precio_anterior) {
		this.precio_anterior = precio_anterior;
	}

	public boolean isNuestros_productos() {
		return nuestros_productos;
	}

	public void setNuestros_productos(boolean nuestros_productos) {
		this.nuestros_productos = nuestros_productos;
	}
	

}
