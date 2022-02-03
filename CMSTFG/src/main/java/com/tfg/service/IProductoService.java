package com.tfg.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.tfg.entity.Producto;


public interface IProductoService {
	
	public List<Producto> findAll(Sort sort);
	
	public Page<Producto> findAll( Pageable pageable );
	
	public Producto findById(long id);
	
	//public Producto findByName(String nombre);
	
	//public Producto findByOferta(String nombre);
	
//	public Producto findByNuestrosProductos();
	
	public void deleteProduct(long id);
	
	//Este método sirve tanto para crear como para actualizar en base de datos, lo
	//detecta él sólo
	public Producto saveProduct(Producto producto);
	
}
