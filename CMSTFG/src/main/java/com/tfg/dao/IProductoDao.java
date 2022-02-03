package com.tfg.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfg.entity.Producto;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

public interface IProductoDao extends JpaRepository<Producto, Long> {
	
	/*funcion que nos devuelve toda la lista de products disponibles
	ordenados por sort que es un tipo que le indicaremos más tarde sobre cómo queremos ordenar*/
	@Query (value = "select p from Producto p")
	public List<Producto> findAll(Sort sort);
	
	/*Devuelve todos los productos de nuestra base de datos a través de una lista paginada.
	 Además no le entregamos el sort porque y va dentro del pageable*/
	@Query (value = "select p from Producto p",
			countQuery ="select count(p) from Producto p")
	public Page<Producto> findAll( Pageable pageable );
	
	@Query (value= "select p from Producto p where p.id = :id" )
	public Producto findById(long id);
	
//	@Query (value = "select p from producto p where p.nombre LIKE  ':name%' ")
//	public Producto findByName(String nombre);
	
//	@Query (value = "select p from producto p where p.oferta = 'true' ")
//	public Producto findByOferta(String nombre);
	
//	@Query (value = "select p from producto p where p.nuestros_productos = 'true' ")
//	public Producto findByNuestrosProductos();
	

}
