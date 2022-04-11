package com.tfg.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class item_pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
    private int cantidad;
    @ManyToOne
    private Historico_precios precio;


    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Historico_precios getPrecio() {
        return precio;
    }

    public void setPrecio(Historico_precios precio) {
        this.precio = precio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public item_pedido(long id, Producto producto, int cantidad, Historico_precios precio) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public item_pedido() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof item_pedido)) return false;
        item_pedido that = (item_pedido) o;
        return getId() == that.getId() && getCantidad() == that.getCantidad() && getProducto().equals(that.getProducto()) && getPrecio().equals(that.getPrecio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProducto(), getCantidad(), getPrecio());
    }
}

