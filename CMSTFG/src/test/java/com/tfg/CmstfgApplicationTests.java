package com.tfg;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.tfg.entity.Producto;
import com.tfg.service.IHistoricoService;
import com.tfg.service.IProductoService;

@SpringBootTest
class CmstfgApplicationTests {
    	private Logger log = Logger.getLogger("Productos");
    	
	@Autowired
	private IProductoService productoService;
    
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

    	private List<Producto> crearYGuardarProductos() {
	    log.info("Creando 4 productos diferentes:");
	    Producto producto1=creaProducto1();
	    Producto producto2=creaProducto2();
	    Producto producto3=creaProducto3();
	    Producto producto4=creaProducto4();
	    log.info("Datos de los 4 productos inicializados con éxito");
	    
	    log.info("Metiendo los productos en base de datos");
	    Producto productoDevuelto1=productoService.saveProduct(producto1);
	    Producto productoDevuelto2=productoService.saveProduct(producto2);
	    Producto productoDevuelto3=productoService.saveProduct(producto3);
	    Producto productoDevuelto4=productoService.saveProduct(producto4);
	    log.info("Productos añadidos con éxito a la base de datos");
	    
	    List<Producto> dev = new ArrayList<Producto>();
	    dev.add(productoDevuelto1);
	    dev.add(productoDevuelto2);
	    dev.add(productoDevuelto3);
	    dev.add(productoDevuelto4);
	    
	    return dev;
    	}

	
	
	public void creaLineas() {
	    log.info("-------------------------------------------------------------------------------------"
	    	+ "----------------------------------------------------------------------------------------"
	    	+ "----------------------------------------------------------------------------------------"
	    	+ "----------------------------------------------------------------------------------------"
	    	+ "----------------------------------------------------------------------------------------"
	    	+ "----------------------------------------------------------------------------------------");

	}
	

	
	@Test
	void testCreaYEliminaProducto() {
	    
	    creaLineas();
	   
	    log.info("Creando 4 productos diferentes:");
	    Producto producto1=creaProducto1();
	    Producto producto2=creaProducto2();
	    Producto producto3=creaProducto3();
	    Producto producto4=creaProducto4();
	    log.info("Datos de los 4 productos inicializados con éxito");
	    
	    log.info("Metiendo los productos en base de datos");
	    Producto productoDevuelto1=productoService.saveProduct(producto1);
	    Producto productoDevuelto2=productoService.saveProduct(producto2);
	    Producto productoDevuelto3=productoService.saveProduct(producto3);
	    Producto productoDevuelto4=productoService.saveProduct(producto4);
	    log.info("Productos añadidos con éxito a la base de datos");

	    
	    log.info("Comprobacion de que los productos son iguales");
	    assertEquals (producto1, productoDevuelto1);
	    assertEquals (producto2, productoDevuelto2);
	    assertEquals (producto3, productoDevuelto3);
	    assertEquals (producto4, productoDevuelto4);
	    log.info("Comprobacion acabada");
	    
	    productoService.deleteProduct(productoDevuelto1.getId());
	    productoService.deleteProduct(productoDevuelto2.getId());
	    productoService.deleteProduct(productoDevuelto3.getId());
	    productoService.deleteProduct(productoDevuelto4.getId());
	    
//	    Sort sortByName = Sort.by("nombre");
//	    Pageable pageable = PageRequest.of(0, 100, sortByName);
//	    
//	    
//	    List<Producto> listaProductosDevueltos = productoService.findAll(sortByName);
//	    assertEquals (producto4, productoDevuelto4);
	    creaLineas();
	}
	
	@Test
	void testBuscaPorIdProducto() {	
	    List <Producto> productosDevueltos = crearYGuardarProductos();
	    long id = productosDevueltos.get(2).getId();
	    log.info("Bucamos el tercer producto añadido");
	    Producto resp=productoService.findById(id);
	    log.info("Comparamos resultados del test");
	    assertEquals (productosDevueltos.get(2).getId(), resp.getId());
	    
	    productoService.deleteProduct(productosDevueltos.get(0).getId());
	    productoService.deleteProduct(productosDevueltos.get(1).getId());
	    productoService.deleteProduct(productosDevueltos.get(2).getId());
	    productoService.deleteProduct(productosDevueltos.get(3).getId());
	    
	    creaLineas();
	}
	
	@Test
	void testBuscaTodoProducto() {	
	    List <Producto> productosDevueltos = crearYGuardarProductos();

	    Sort sortByName = Sort.by("nombre");
	   // Pageable pageable = PageRequest.of(0, 100, sortByName);
	    
	    
	    List<Producto> listaProductosDevueltos = productoService.findAll(sortByName);
	    
	    
	    log.info("El primer elemento por orden alabético (Limon, id = 3)");
	    assertEquals (productosDevueltos.get(3), listaProductosDevueltos.get(1));	
	    
	    productoService.deleteProduct(productosDevueltos.get(0).getId());
	    productoService.deleteProduct(productosDevueltos.get(1).getId());
	    productoService.deleteProduct(productosDevueltos.get(2).getId());
	    productoService.deleteProduct(productosDevueltos.get(3).getId());
	    creaLineas();
	}
	
	@Test
	void testBuscaTodoProductoPageable() {	
	    List <Producto> productosDevueltos = crearYGuardarProductos();

	    Sort sortByName = Sort.by("nombre");
	    Pageable pageable = PageRequest.of(0, 2, sortByName);
	    
	    
	    Page<Producto> listaProductosDevueltos = productoService.findAll(pageable);
	    
	    
	    log.info("");
	    assertEquals (productosDevueltos.get(0), listaProductosDevueltos.getContent().get(0));	
	    
	    productoService.deleteProduct(productosDevueltos.get(0).getId());
	    productoService.deleteProduct(productosDevueltos.get(1).getId());
	    productoService.deleteProduct(productosDevueltos.get(2).getId());
	    productoService.deleteProduct(productosDevueltos.get(3).getId());
	    creaLineas();
	}
	
	@Test
	void testBuscaProductoPorNombre() {	
	    List <Producto> productosDevueltos = crearYGuardarProductos();
	    
	    
	    List<Producto> listaProductosDevueltos = productoService.findByName("Fresa");
	    
	    
	    log.info("");
	    assertEquals (productosDevueltos.get(0), listaProductosDevueltos.get(0));	
	    
	    productoService.deleteProduct(productosDevueltos.get(0).getId());
	    productoService.deleteProduct(productosDevueltos.get(1).getId());
	    productoService.deleteProduct(productosDevueltos.get(2).getId());
	    productoService.deleteProduct(productosDevueltos.get(3).getId());
	    creaLineas();
	}
	
	@Test
	void testBuscaProductoPorOferta() {	
	    List <Producto> productosDevueltos = crearYGuardarProductos();
	    
	    
	    List<Producto> listaProductosDevueltos = productoService.findByOferta();
	    
	    
	    log.info("");
	    assertEquals (2, listaProductosDevueltos.size());
	    assertEquals(productosDevueltos.get(2),listaProductosDevueltos.get(0));
	    assertEquals(productosDevueltos.get(3),listaProductosDevueltos.get(1));
	    
	    productoService.deleteProduct(productosDevueltos.get(0).getId());
	    productoService.deleteProduct(productosDevueltos.get(1).getId());
	    productoService.deleteProduct(productosDevueltos.get(2).getId());
	    productoService.deleteProduct(productosDevueltos.get(3).getId());
	    creaLineas();
	}
	
	@Test
	void testBuscaProductoPorNuestros_productos() {	
	    List <Producto> productosDevueltos = crearYGuardarProductos();
	    
	    
	    List<Producto> listaProductosDevueltos = productoService.findByNuestrosProductos();
	    
	    
	    log.info("");
	    assertEquals (2, listaProductosDevueltos.size());
	    assertEquals(productosDevueltos.get(1),listaProductosDevueltos.get(0));
	    assertEquals(productosDevueltos.get(3),listaProductosDevueltos.get(1));
	    
	    productoService.deleteProduct(productosDevueltos.get(0).getId());
	    productoService.deleteProduct(productosDevueltos.get(1).getId());
	    productoService.deleteProduct(productosDevueltos.get(2).getId());
	    productoService.deleteProduct(productosDevueltos.get(3).getId());
	    creaLineas();
	}
	
	@Test
	void testActualizaProducto() {	
	    List <Producto> productosDevueltos = crearYGuardarProductos();
	    
	    
	    Producto productoAActualizar = productoService.findById(productosDevueltos.get(0).getId());
	    
	    
	    productoAActualizar.setCantidad(9);
	    productoAActualizar.setNombre("Fresas 2.0");
	    productoAActualizar.setNuestros_productos(true);
	    productoAActualizar.setOferta(true);
	    productoAActualizar.setPrecio(2.5f);
	    
	    productoService.saveProduct(productoAActualizar);
	    
	    Producto productoActualizado = productoService.findById(1);
	    log.info("");
	    assertEquals (9, productoAActualizar.getCantidad());
	    assertEquals ("Fresas 2.0", productoAActualizar.getNombre());
	    assertEquals (true, productoAActualizar.isNuestros_productos());
	    assertEquals (true, productoAActualizar.isOferta());
	    assertEquals (2.5f, productoAActualizar.getPrecio());

	    
	    productoService.deleteProduct(productosDevueltos.get(0).getId());
	    productoService.deleteProduct(productosDevueltos.get(1).getId());
	    productoService.deleteProduct(productosDevueltos.get(2).getId());
	    productoService.deleteProduct(productosDevueltos.get(3).getId());
	    creaLineas();
	}
}
