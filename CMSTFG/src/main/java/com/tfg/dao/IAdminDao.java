package com.tfg.dao;


import com.tfg.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IAdminDao extends JpaRepository<Admin, Long> {

    @Query(value= "select p from Admin p where p.username = :username" )
    public Admin findByUsername(String username);


}