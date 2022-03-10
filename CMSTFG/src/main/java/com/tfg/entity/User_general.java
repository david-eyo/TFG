package com.tfg.entity;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class User_general extends User{
    public User_general() {
        this.setRol(Rol.ROLE_USER);
    }

    public User_general(long id, String username, String password, String nombre, String apellidos, String email, String ciudad, String cp, String direccion, Date fechaNacimiento, String tlf) {
        super(id, username, password, nombre, apellidos, email, Rol.ROLE_USER, ciudad, cp, direccion, fechaNacimiento, tlf);
    }


}
