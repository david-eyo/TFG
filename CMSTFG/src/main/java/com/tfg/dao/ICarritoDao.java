package com.tfg.dao;

import com.tfg.entity.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ICarritoDao extends JpaRepository<Carrito, Long> {
    @Query(value= "select h from Carrito h where h.id = :id" )
    public Carrito findCarritoById(long id);

    @Query(value= "select h from Carrito h where h.usuario.username = :username" )
    public List<Carrito> findCarritoByUsername(String username);

    @Modifying
    @Query(value= "insert INTO Carrito (productos_id, usuario_id,  cantidad" +
            ") values (:productoId, :userId, :cantidad)", nativeQuery= true )
    @Transactional
    public void saveProducto(long productoId, long userId, long cantidad);

    @Query(value="SELECT count(c)>0 from Carrito c WHERE ((c.productos.id = :productId) and (c.usuario.id = :userId))")
    public Boolean productoYaEnCarrito(Long productId, Long userId);

}
