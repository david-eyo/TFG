package com.tfg.dao;

import com.tfg.entity.Producto;
import com.tfg.entity.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ITrabajoDao extends JpaRepository<Trabajo, Long> {

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    @Query(value = "select t from Trabajo t where t.trabajador.username= :name ")
    public List<Trabajo> findTrabajoByUsername(String name);

    @Query(value = "select t from Trabajo t where (" +
            "(t.inicioTrabajo > :fechaInicio) and (t.inicioTrabajo < :fechaFin) )")
    public List<Trabajo> findTrabajoByFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    @Query(value = "select t from Trabajo t where t.localizacion = :localizacion ")
    public List<Trabajo> findTrabajoByLocalizacion(String localizacion);

    @Query(value = "select t from Trabajo t where t.tipo_trabajo = :tipo_trabajo ")
    public List<Trabajo> findTrabajoByTipoTrabajo(String tipo_trabajo);

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    @Query(value = "select t from Trabajo t where ((t.trabajador.username= :username) AND " +
            "(t.inicioTrabajo > :fechaInicio) and (t.inicioTrabajo < :fechaFin) )")
    public List<Trabajo> findTrabajoByUsuarioyFechas(String username, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    @Query(value = "select t from Trabajo t where ((t.trabajador.username= :username) AND " +
            "(t.localizacion = :localizacion) )")
    public List<Trabajo> findTrabajoByUsuarioyLocalizacion(String username, String localizacion);

    @Query(value = "select t from Trabajo t where ((t.trabajador.username= :username) AND " +
            "(t.tipo_trabajo = :tipo_trabajo) )")
    public List<Trabajo> findTrabajoByUsuarioyTipo_trabajo(String username, String tipo_trabajo);

    @Query(value = "select t from Trabajo t where ((t.localizacion = :localizacion) AND " +
            "(t.inicioTrabajo > :fechaInicio) and (t.inicioTrabajo < :fechaFin) )")
    public List<Trabajo> findTrabajoByFechasYLocalizacion(LocalDateTime fechaInicio, LocalDateTime fechaFin,String localizacion );

    @Query(value = "select t from Trabajo t where ((t.tipo_trabajo = :tipo_trabajo) AND " +
            "(t.inicioTrabajo > :fechaInicio) and (t.inicioTrabajo < :fechaFin) )")
    public List<Trabajo> findTrabajoByFechasYTipo_trabajo(LocalDateTime fechaInicio, LocalDateTime fechaFin, String tipo_trabajo);

    @Query(value = "select t from Trabajo t where ((t.tipo_trabajo = :tipo_trabajo) AND " +
            "(t.localizacion = :localizacion) )")
    public List<Trabajo> findTrabajoByLocalizacionYTipo_trabajo(String localizacion, String tipo_trabajo);

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    @Query(value = "select t from Trabajo t where ((t.trabajador.username= :username) AND " +
            "(t.inicioTrabajo > :fechaInicio) and (t.inicioTrabajo < :fechaFin) AND (t.localizacion = :localizacion))")
    public List<Trabajo> findTrabajoByUsuarioFechasyLocalizacion(String username, LocalDateTime fechaInicio, LocalDateTime fechaFin, String localizacion);

    @Query(value = "select t from Trabajo t where ((t.trabajador.username= :username) AND " +
            "(t.inicioTrabajo > :fechaInicio) and (t.inicioTrabajo < :fechaFin) AND (t.tipo_trabajo = :tipo_trabajo))")
    public List<Trabajo> findTrabajoByUsuarioFechasyTipo_trabajo(String username, LocalDateTime fechaInicio, LocalDateTime fechaFin, String tipo_trabajo);

    @Query(value = "select t from Trabajo t where ((t.trabajador.username= :username) AND " +
            "(t.localizacion = :localizacion) AND (t.tipo_trabajo = :tipo_trabajo))")
    List<Trabajo> findTrabajoByUsuarioLocalizacionYTipo_trabajo(String username, String localizacion, String tipo_trabajo);

    @Query(value = "select t from Trabajo t where ((t.tipo_trabajo = :tipo_trabajo) AND " +
            "(t.inicioTrabajo > :fechaInicio) and (t.inicioTrabajo < :fechaFin) AND (t.localizacion = :localizacion))")
    public List<Trabajo> findTrabajoByFechasLocalizacionyTipo_trabajo(LocalDateTime fechaInicio, LocalDateTime fechaFin,String localizacion, String tipo_trabajo);

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    @Query(value = "select t from Trabajo t where ((t.trabajador.username= :username) AND " +
            "(t.inicioTrabajo > :fechaInicio) and (t.inicioTrabajo < :fechaFin) AND (t.localizacion = :localizacion) AND (t.tipo_trabajo = :tipo_trabajo))")
    public List<Trabajo> findTrabajoByUsuarioFechasLocalizacionyTipo_trabajo(String username, LocalDateTime fechaInicio, LocalDateTime fechaFin,String localizacion, String tipo_trabajo);


    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    @Query(value = "select t from Trabajo t where ((t.trabajador.username= :username) AND (t.finalTrabajo IS NULL))")
    public Trabajo findTrabajoSinFinalizarDeUsuario(String username);

    @Query(value = "select t from Trabajo t where (t.id = :id)")
    public Trabajo findTrabajobyId(long id);

}
