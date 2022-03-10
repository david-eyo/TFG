package com.tfg.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {

    public enum Rol {

        ROLE_ADMIN,
        ROLE_USER,
        ROLE_WORKER;

    }

    @Id
    @GeneratedValue
    private long id;
    @NotEmpty(message= "El campo username no puede ser nulo")
    private String username;
    private String password;
    @Size(min = 2, max = 40, message = "Nombre debe tener entre 2 y 40 caracteres")
    private String nombre;
    @Size(min = 2, max = 100, message = "Apellidos debe tener entre 2 y 100 caracteres")
    private String apellidos;
    private String email;
    //@NotNull(message= "El campo rol no puede ser nulo")
    private Rol rol;
    @Size(min = 2, max = 40, message = "Nombre debe tener entre 2 y 40 caracteres")
    private String ciudad;
    @Size(min = 4, max = 6, message = "CP debe tener entre 3 y 5 caracteres")
    private String cp;
    @Size(min = 2, max = 40, message = "Nombre debe tener entre 2 y 40 caracteres")
    private String direccion;
    private Date fechaNacimiento;
    @Size(min = 9, message = "Teléfono debe tener más de 9 caracteres")
    private String tlf;


    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public User(long id, String username, String password, String nombre, String apellidos, String email, Rol rol,
                String ciudad, String cp, String direccion, Date fechaNacimiento, String tlf) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(
                getPassword(), user.getPassword()) && Objects.equals(getNombre(), user.getNombre()) &&
                Objects.equals(getApellidos(), user.getApellidos()) && Objects.equals(getEmail(), user.getEmail())
                && getRol() == user.getRol() && Objects.equals(getCiudad(), user.getCiudad()) && Objects.equals(getCp(),
                user.getCp()) && Objects.equals(getDireccion(), user.getDireccion()) && Objects.equals(getFechaNacimiento(),
                user.getFechaNacimiento()) && Objects.equals(getTlf(), user.getTlf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getNombre(), getApellidos(), getEmail(), getRol(),
                getCiudad(), getCp(), getDireccion(), getFechaNacimiento(), getTlf());
    }
}
