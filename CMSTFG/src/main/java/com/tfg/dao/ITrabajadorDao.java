package com.tfg.dao;


import com.tfg.entity.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ITrabajadorDao extends JpaRepository<Trabajador, Long> {

    @Query(value= "select p from User p where (p.username = :username) and (p.rol=2)" )
    public Trabajador findByUsername(String username);


}
