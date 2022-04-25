package com.tfg.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Trabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.MERGE  )
    private Trabajador trabajador;

    @NotNull(message= "El campo fecha de inicio no puede ser nulo")
    private LocalDateTime inicioTrabajo;

    private LocalDateTime finalTrabajo;

    private String localizacion;

    private String tipo_trabajo;

    private String observaciones;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public LocalDateTime getInicioTrabajo() {
        return inicioTrabajo;
    }

    public void setInicioTrabajo(LocalDateTime inicioTrabajo) {
        this.inicioTrabajo = inicioTrabajo;
    }

    public LocalDateTime getFinalTrabajo() {
        return finalTrabajo;
    }

    public void setFinalTrabajo(LocalDateTime finalTrabajo) {
        this.finalTrabajo = finalTrabajo;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getTipo_trabajo() {
        return tipo_trabajo;
    }

    public void setTipo_trabajo(String tipo_trabajo) {
        this.tipo_trabajo = tipo_trabajo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Trabajo(long id, Trabajador trabajador, LocalDateTime inicioTrabajo, LocalDateTime finalTrabajo, String localizacion, String tipo_trabajo, String observaciones) {
        this.id = id;
        this.trabajador = trabajador;
        this.inicioTrabajo = inicioTrabajo;
        this.finalTrabajo = finalTrabajo;
        this.localizacion = localizacion;
        this.tipo_trabajo = tipo_trabajo;
        this.observaciones = observaciones;
    }

    public Trabajo() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trabajo)) return false;
        Trabajo trabajo = (Trabajo) o;
        return getId() == trabajo.getId() && Objects.equals(getTrabajador(), trabajo.getTrabajador()) && Objects.equals(getInicioTrabajo(), trabajo.getInicioTrabajo()) && Objects.equals(getFinalTrabajo(), trabajo.getFinalTrabajo()) && Objects.equals(getLocalizacion(), trabajo.getLocalizacion()) && Objects.equals(getTipo_trabajo(), trabajo.getTipo_trabajo()) && Objects.equals(getObservaciones(), trabajo.getObservaciones());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTrabajador(), getInicioTrabajo(), getFinalTrabajo(), getLocalizacion(), getTipo_trabajo(), getObservaciones());
    }
}
