package com.tfg.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Table
public class Pedido {

    public enum Estado_envio {

        NOTIFICADO,
        ENVIADO,
        ENTREGA,
        ENTREGADO;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private User usuario;

    @OneToMany
    private List<item_pedido> item_pedido;

    private Estado_envio estado;

    private LocalDateTime fechaPedido;

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

    public List<com.tfg.entity.item_pedido> getItem_pedido() {
        return item_pedido;
    }

    public void setItem_pedido(List<com.tfg.entity.item_pedido> item_pedido) {
        this.item_pedido = item_pedido;
    }

    public Estado_envio getEstado() {
        return estado;
    }

    public void setEstado(Estado_envio estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Pedido(long id, User usuario, List<com.tfg.entity.item_pedido> item_pedido, Estado_envio estado, LocalDateTime fechaPedido) {
        this.id = id;
        this.usuario = usuario;
        this.item_pedido = item_pedido;
        this.estado = estado;
        this.fechaPedido = fechaPedido;
    }

    public Pedido() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido)) return false;
        Pedido pedido = (Pedido) o;
        return getId() == pedido.getId() && getUsuario().equals(pedido.getUsuario()) && getItem_pedido().equals(pedido.getItem_pedido()) && getEstado() == pedido.getEstado() && getFechaPedido().equals(pedido.getFechaPedido());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsuario(), getItem_pedido(), getEstado(), getFechaPedido());
    }
}
