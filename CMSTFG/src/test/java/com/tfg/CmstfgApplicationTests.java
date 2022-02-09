package com.tfg;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.tfg.controller.ProductoController;
import com.tfg.entity.Producto;

@SpringBootTest
class CmstfgApplicationTests {
    
    	private Producto creaProducto1() {
    	    Producto producto = new Producto();
    	    producto.setNombre("Fresas");
    	    producto.setPrecio(1.5f);
    	    producto.setCantidad(6);
    	    producto.setNuestros_productos(false);
    	    producto.setOferta(false);
    	    return producto;
    	}
    	
    	private Producto creaProducto2() {
    	    Producto producto = new Producto();
    	    producto.setNombre("Mandarina");
    	    producto.setPrecio(1.33f);
    	    producto.setCantidad(16);
    	    producto.setNuestros_productos(true);
    	    producto.setOferta(false);
    	    return producto;
    	}
    	
    	private Producto creaProducto3() {
    	    Producto producto = new Producto();
    	    producto.setNombre("Pomelo");
    	    producto.setPrecio(2.5f);
    	    producto.setCantidad(4);
    	    producto.setNuestros_productos(false);
    	    producto.setOferta(true);
    	    return producto;
    	}
    	
    	private Producto creaProducto4() {
    	    Producto producto = new Producto();
    	    producto.setNombre("Limon");
    	    producto.setPrecio(0.99f);
    	    producto.setCantidad(20);
    	    producto.setNuestros_productos(true);
    	    producto.setOferta(true);
    	    return producto;
    	}


	@Test
	void contextLoads() {
	}
	
	@Test
	void creaProducto() {
	    ProductoController productorController = new ProductoController();
	    
	}

}
