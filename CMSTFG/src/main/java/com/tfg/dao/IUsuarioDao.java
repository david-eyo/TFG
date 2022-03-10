package com.tfg.dao;


import com.tfg.entity.Historico_precios;
import com.tfg.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUsuarioDao extends JpaRepository<User, Long> {

    @Query(value= "select p from User p where p.username = :username" )
    public User findByUsername(String username);

    @Query (value= "select h from User h where h.id = :id" )
    public User findUserById(long id);


}
