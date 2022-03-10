package com.tfg.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
public class Carrito implements Serializable{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne( fetch = FetchType.EAGER, cascade = CascadeType.MERGE  )
    private User usuario;


    @OneToOne( fetch = FetchType.EAGER, cascade = CascadeType.MERGE  )
    private Producto productos;

    private int cantidad;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Producto getProductos() {
        return productos;
    }

    public void setProductos(Producto productos) {
        this.productos = productos;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Carrito() {
    }

    public Carrito(long id, User_general usuario, Producto productos, int cantidad) {
        this.id = id;
        this.usuario = usuario;
        this.productos = productos;
        this.cantidad = cantidad;
    }
}
