package com.tfg.service;

import com.tfg.entity.Trabajo;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ITrabajoService {


     List<Trabajo> findTrabajoByUsername(String name);

     List<Trabajo> findTrabajoByFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

     List<Trabajo> findTrabajoByLocalizacion(String localizacion);

     List<Trabajo> findTrabajoByTipoTrabajo(String tipo_trabajo);

     List<Trabajo> findTrabajoByUsuarioyFechas(String username, LocalDateTime fechaInicio, LocalDateTime fechaFin);

     List<Trabajo> findTrabajoByUsuarioyLocalizacion(String username, String localizacion);

     List<Trabajo> findTrabajoByUsuarioyTipo_trabajo(String username, String tipo_trabajo);

     List<Trabajo> findTrabajoByFechasYLocalizacion(LocalDateTime fechaInicio, LocalDateTime fechaFin,String localizacion );

     List<Trabajo> findTrabajoByFechasYTipo_trabajo(LocalDateTime fechaInicio, LocalDateTime fechaFin, String tipo_trabajo);

     List<Trabajo> findTrabajoByLocalizacionYTipo_trabajo(String localizacion, String tipo_trabajo);

     List<Trabajo> findTrabajoByUsuarioFechasyLocalizacion(String username, LocalDateTime fechaInicio, LocalDateTime fechaFin, String localizacion);

     List<Trabajo> findTrabajoByUsuarioLocalizacionYTipo_trabajo(String username, String localizacion, String tipo_trabajo);

     List<Trabajo> findTrabajoByUsuarioFechasyTipo_trabajo(String username, LocalDateTime fechaInicio, LocalDateTime fechaFin, String tipo_trabajo);

     List<Trabajo> findTrabajoByFechasLocalizacionyTipo_trabajo(LocalDateTime fechaInicio, LocalDateTime fechaFin,String localizacion, String tipo_trabajo);

     List<Trabajo> findTrabajoByUsuarioFechasLocalizacionyTipo_trabajo(String username, LocalDateTime fechaInicio, LocalDateTime fechaFin,String localizacion, String tipo_trabajo);

     Trabajo saveTrabajo (Trabajo trabajo);

     void deleteTrabajo(long id);

     Trabajo findTrabajoSinFinalizarDeUsuario(String username);

     Trabajo findTrabajobyId(long id);


}
