package com.tfg.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {

    enum Rol {

        ROLE_ADMIN,
        ROLE_USER,
        ROLE_WORKER;

    }

    @Id
    @GeneratedValue
    private int id;
    @NotEmpty(message= "El campo username no puede ser nulo")
    private String username;
    @NotEmpty(message= "El campo contraseña no puede ser nulo")
    private String password;
    @NotEmpty(message= "El campo nombre no puede ser nulo") @Size(min = 2, max = 40, message = "Nombre debe tener entre 2 y 40 caracteres")
    private String nombre;
    @NotEmpty(message= "El campo apellidos no puede ser nulo") @Size(min = 2, max = 100, message = "Apellidos debe tener entre 2 y 100 caracteres")
    private String apellidos;
    @NotEmpty(message= "El campo email no puede ser nulo")
    private String email;
    //@NotNull(message= "El campo rol no puede ser nulo")
    private Rol rol;
    @NotEmpty(message= "El campo ciudad no puede ser nulo") @Size(min = 2, max = 40, message = "Nombre debe tener entre 2 y 40 caracteres")
    private String ciudad;
    @NotEmpty(message= "El campo cp no puede ser nulo") @Size(min = 4, max = 6, message = "CP debe tener entre 3 y 5 caracteres")
    private String cp;
    @NotEmpty(message= "El campo direccion no puede ser nulo") @Size(min = 2, max = 40, message = "Nombre debe tener entre 2 y 40 caracteres")
    private String direccion;
    @NotNull(message= "El campo fecha Nacimiento no puede ser nulo")
    private Date fechaNacimiento;
    @NotEmpty(message= "El campo direccion no puede ser nulo") @Size(min = 9, message = "Teléfono debe tener más de 9 caracteres")
    private String tlf;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Rol getRol() {
        return rol;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public User() {
    }

    public User(int id, String username, String password, String nombre, String apellidos, String email, Rol rol,
                String ciudad, String cp, String direccion, Date fechaNacimiento, String tlf, String nss) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.rol = rol;
        this.ciudad = ciudad;
        this.cp = cp;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.tlf = tlf;

    }

    public String toStringRol() {
        return ""+rol;
    }
}
