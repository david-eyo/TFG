package com.tfg.service;

import com.tfg.entity.Carrito;

import java.util.List;

public interface ICarritoService {


    public Carrito findCarritoById(long id);

    public  List<Carrito> findCarritoByUsername(String username);

    public Carrito save(Carrito carrito);

    public void saveProducto(long productoId, long userId, long cantidad);

    public void delete(long id);

    public Boolean productoYaEnCarrito(Long productId, Long userId);
}
