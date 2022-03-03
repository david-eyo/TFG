package com.tfg.dao;


import com.tfg.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUsuarioDao extends JpaRepository<User, Long> {

    @Query(value= "select p from User p where p.username = :username" )
    public User findByUsername(String username);


}
