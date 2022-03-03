package com.tfg.dao;


import com.tfg.entity.User_general;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUser_generalDao extends JpaRepository<User_general, Long> {

    @Query(value= "select p from User_general p where p.username = :username" )
    public User_general findByUsername(String username);


}
