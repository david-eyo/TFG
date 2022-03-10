package com.tfg.dao;


import com.tfg.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IAdminDao extends JpaRepository<Admin, Long> {

    @Query(value= "select p from User p where (p.username = :username) and (p.rol=0)" )
    public Admin findByUsername(String username);


}