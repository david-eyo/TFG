package com.tfg.service;

import com.tfg.dao.ICarritoDao;
import com.tfg.entity.Carrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoServiceImpl implements ICarritoService{

    @Autowired
    private ICarritoDao carritoDao;

    @Override
    public Carrito findCarritoById(long id) {
        return carritoDao.findCarritoById(id);
    }

    @Override
    public  List<Carrito> findCarritoByUsername(String username) {
        return carritoDao.findCarritoByUsername(username);
    }

    @Override
    public Carrito save(Carrito carrito) {
        return carritoDao.save(carrito);
    }

    public void saveProducto(long productoId, long userId, long cantidad){
        carritoDao.saveProducto(productoId, userId, cantidad);
    }

    @Override
    public void delete(long id) {
        carritoDao.deleteById(id);
    }

    @Override
    public Boolean productoYaEnCarrito(Long productId, Long userId){
        return carritoDao.productoYaEnCarrito(productId, userId);
    }
}
