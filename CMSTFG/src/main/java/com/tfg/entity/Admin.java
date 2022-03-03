package com.tfg.entity;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Admin extends User{
    public Admin() {
        this.setRol(Rol.ROLE_ADMIN);

    }

    public Admin(int id, String username, String password, String nombre, String apellidos, String email, String ciudad, String cp, String direccion, Date fechaNacimiento, String tlf, String nss) {
        super(id, username, password, nombre, apellidos, email, Rol.ROLE_ADMIN, ciudad, cp, direccion, fechaNacimiento, tlf, nss);
    }
}
