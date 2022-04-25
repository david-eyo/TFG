package com.tfg.service;

import com.tfg.dao.ITrabajoDao;
import com.tfg.entity.Trabajo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrabajoServiceImpl implements ITrabajoService{
    @Autowired
    private ITrabajoDao trabajoDao;
    @Override
    public List<Trabajo> findTrabajoByUsername(String name) {
        return trabajoDao.findTrabajoByUsername(name);
    }

    @Override
    public List<Trabajo> findTrabajoByFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return trabajoDao.findTrabajoByFechas(fechaInicio, fechaFin);
    }

    @Override
    public List<Trabajo> findTrabajoByLocalizacion(String localizacion) {
        return trabajoDao.findTrabajoByLocalizacion(localizacion);
    }

    @Override
    public List<Trabajo> findTrabajoByTipoTrabajo(String tipo_trabajo) {
        return trabajoDao.findTrabajoByTipoTrabajo(tipo_trabajo);
    }

    @Override
    public List<Trabajo> findTrabajoByUsuarioyFechas(String username, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return trabajoDao.findTrabajoByUsuarioyFechas(username, fechaInicio, fechaFin);
    }

    @Override
    public List<Trabajo> findTrabajoByUsuarioyLocalizacion(String username, String localizacion) {
        return trabajoDao.findTrabajoByUsuarioyLocalizacion(username, localizacion);
    }

    @Override
    public List<Trabajo> findTrabajoByUsuarioyTipo_trabajo(String username, String tipo_trabajo) {
        return trabajoDao.findTrabajoByUsuarioyTipo_trabajo(username, tipo_trabajo);
    }

    @Override
    public List<Trabajo> findTrabajoByFechasYLocalizacion(LocalDateTime fechaInicio, LocalDateTime fechaFin, String localizacion) {
        return trabajoDao.findTrabajoByFechasYLocalizacion(fechaInicio, fechaFin, localizacion);
    }

    @Override
    public List<Trabajo> findTrabajoByFechasYTipo_trabajo(LocalDateTime fechaInicio, LocalDateTime fechaFin, String tipo_trabajo) {
        return trabajoDao.findTrabajoByFechasYTipo_trabajo(fechaInicio, fechaFin, tipo_trabajo);
    }

    @Override
    public List<Trabajo> findTrabajoByLocalizacionYTipo_trabajo(String localizacion, String tipo_trabajo) {
        return trabajoDao.findTrabajoByLocalizacionYTipo_trabajo(localizacion, tipo_trabajo);
    }

    @Override
    public List<Trabajo> findTrabajoByUsuarioFechasyLocalizacion(String username, LocalDateTime fechaInicio, LocalDateTime fechaFin, String localizacion) {
        return trabajoDao.findTrabajoByUsuarioFechasyLocalizacion(username, fechaInicio, fechaFin, localizacion);
    }

    @Override
    public List<Trabajo> findTrabajoByUsuarioFechasyTipo_trabajo(String username, LocalDateTime fechaInicio, LocalDateTime fechaFin, String tipo_trabajo) {
        return trabajoDao.findTrabajoByUsuarioFechasyTipo_trabajo(username, fechaInicio, fechaFin, tipo_trabajo);
    }

    @Override
    public List<Trabajo> findTrabajoByUsuarioLocalizacionYTipo_trabajo(String username, String localizacion, String tipo_trabajo) {
        return trabajoDao.findTrabajoByUsuarioLocalizacionYTipo_trabajo(username,  localizacion,  tipo_trabajo);
    }

    @Override
    public List<Trabajo> findTrabajoByFechasLocalizacionyTipo_trabajo(LocalDateTime fechaInicio, LocalDateTime fechaFin, String localizacion, String tipo_trabajo) {
        return trabajoDao.findTrabajoByFechasLocalizacionyTipo_trabajo(fechaInicio, fechaFin, localizacion, tipo_trabajo);
    }

    @Override
    public List<Trabajo> findTrabajoByUsuarioFechasLocalizacionyTipo_trabajo(String username, LocalDateTime fechaInicio, LocalDateTime fechaFin, String localizacion, String tipo_trabajo) {
        return trabajoDao.findTrabajoByUsuarioFechasLocalizacionyTipo_trabajo(username, fechaInicio, fechaFin, localizacion, tipo_trabajo);
    }

    @Override
    public Trabajo saveTrabajo(Trabajo trabajo) {
        return trabajoDao.save(trabajo);
    }


    public void deleteTrabajo(long id) {
        trabajoDao.deleteById(id);
    }

    @Override
    public Trabajo findTrabajoSinFinalizarDeUsuario(String username){return trabajoDao.findTrabajoSinFinalizarDeUsuario(username);}

    @Override
    public Trabajo findTrabajobyId(long id){return trabajoDao.findTrabajobyId(id);}

}
