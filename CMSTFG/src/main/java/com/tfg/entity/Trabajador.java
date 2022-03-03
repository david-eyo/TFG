package com.tfg.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Trabajador extends User{

    @NotEmpty(message= "El campo numero de seguridad social no puede ser nulo")
    @Size(min = 9, message = "Numero de seguridad social debe tener más de 9 caracteres")
    private String nss;

    @NotEmpty(message= "El campo dni no puede ser nulo")
    @Size(min = 9, message = "DNI debe tener más de 9 caracteres")
    private String dni;

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Trabajador(String nss, String dni) {
        this.nss = nss;
        this.dni = dni;
    }

    public Trabajador(int id, String username, String password, String nombre, String apellidos, String email, String ciudad, String cp, String direccion, Date fechaNacimiento, String tlf, String nss, String nss1, String dni) {
        super(id, username, password, nombre, apellidos, email, Rol.ROLE_WORKER, ciudad, cp, direccion, fechaNacimiento, tlf, nss);
        this.nss = nss1;
        this.dni = dni;
    }

    public Trabajador() {
        this.setRol(Rol.ROLE_WORKER);

    }

    public Trabajador(int id, String username, String password, String nombre, String apellidos, String email, String ciudad, String cp, String direccion, Date fechaNacimiento, String tlf, String nss) {
        super(id, username, password, nombre, apellidos, email, Rol.ROLE_WORKER, ciudad, cp, direccion, fechaNacimiento, tlf, nss);
    }
}
