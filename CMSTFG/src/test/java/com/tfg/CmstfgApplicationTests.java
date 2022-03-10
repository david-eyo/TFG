package com.tfg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.management.InstanceNotFoundException;
import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.tfg.entity.Historico_precios;
import com.tfg.entity.Producto;
import com.tfg.service.IHistoricoService;
import com.tfg.service.IProductoService;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
class CmstfgApplicationTests {
	private Logger log = Logger.getLogger("Productos");

	@Autowired
	private IProductoService productoService;

	@Autowired
	private IHistoricoService historicoService;

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
		Producto producto1 = creaProducto1();
		Producto producto2 = creaProducto2();
		Producto producto3 = creaProducto3();
		Producto producto4 = creaProducto4();
		log.info("Datos de los 4 productos inicializados con éxito");

		log.info("Metiendo los productos en base de datos");
		Producto productoDevuelto1 = productoService.saveProduct(producto1);
		Producto productoDevuelto2 = productoService.saveProduct(producto2);
		Producto productoDevuelto3 = productoService.saveProduct(producto3);
		Producto productoDevuelto4 = productoService.saveProduct(producto4);
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
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	void testCreaYEliminaProducto() {

		creaLineas();

		log.info("Creando 4 productos diferentes:");
		Producto producto1 = creaProducto1();
		Producto producto2 = creaProducto2();
		Producto producto3 = creaProducto3();
		Producto producto4 = creaProducto4();
		log.info("Datos de los 4 productos inicializados con éxito");

		log.info("Metiendo los productos en base de datos");
		Producto productoDevuelto1 = productoService.saveProduct(producto1);
		Producto productoDevuelto2 = productoService.saveProduct(producto2);
		Producto productoDevuelto3 = productoService.saveProduct(producto3);
		Producto productoDevuelto4 = productoService.saveProduct(producto4);
		log.info("Productos añadidos con éxito a la base de datos");

		log.info("Comprobacion de que los productos son iguales");
		assertEquals(producto1, productoDevuelto1);
		assertEquals(producto2, productoDevuelto2);
		assertEquals(producto3, productoDevuelto3);
		assertEquals(producto4, productoDevuelto4);
		log.info("Comprobacion acabada");

		productoService.deleteProduct(productoDevuelto1.getId());
		productoService.deleteProduct(productoDevuelto2.getId());
		productoService.deleteProduct(productoDevuelto3.getId());
		productoService.deleteProduct(productoDevuelto4.getId());
		creaLineas();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	void testBuscaPorIdProducto() {
		List<Producto> productosDevueltos = crearYGuardarProductos();
		long id = productosDevueltos.get(2).getId();
		log.info("Bucamos el tercer producto añadido");
		Producto resp = productoService.findById(id);
		log.info("Comparamos resultados del test");
		assertEquals(productosDevueltos.get(2).getId(), resp.getId());

		productoService.deleteProduct(productosDevueltos.get(0).getId());
		productoService.deleteProduct(productosDevueltos.get(1).getId());
		productoService.deleteProduct(productosDevueltos.get(2).getId());
		productoService.deleteProduct(productosDevueltos.get(3).getId());

		creaLineas();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	void testBuscaTodoProducto() {
		List<Producto> productosDevueltos = crearYGuardarProductos();

		Sort sortByName = Sort.by("nombre");

		List<Producto> listaProductosDevueltos = productoService.findAll(sortByName);

		log.info("El primer elemento por orden alabético (Limon, id = 3)");
		assertEquals(productosDevueltos.get(3), listaProductosDevueltos.get(1));

		productoService.deleteProduct(productosDevueltos.get(0).getId());
		productoService.deleteProduct(productosDevueltos.get(1).getId());
		productoService.deleteProduct(productosDevueltos.get(2).getId());
		productoService.deleteProduct(productosDevueltos.get(3).getId());
		creaLineas();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	void testBuscaTodoProductoPageable() {
		List<Producto> productosDevueltos = crearYGuardarProductos();

		Sort sortByName = Sort.by("nombre");
		Pageable pageable = PageRequest.of(0, 2, sortByName);

		Page<Producto> listaProductosDevueltos = productoService.findAll(pageable);

		log.info("");
		assertEquals(productosDevueltos.get(0), listaProductosDevueltos.getContent().get(0));

		productoService.deleteProduct(productosDevueltos.get(0).getId());
		productoService.deleteProduct(productosDevueltos.get(1).getId());
		productoService.deleteProduct(productosDevueltos.get(2).getId());
		productoService.deleteProduct(productosDevueltos.get(3).getId());
		creaLineas();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	void testBuscaProductoPorNombre() {
		List<Producto> productosDevueltos = crearYGuardarProductos();

		List<Producto> listaProductosDevueltos = productoService.findByName("Fresa");

		log.info("");
		assertEquals(productosDevueltos.get(0), listaProductosDevueltos.get(0));

		productoService.deleteProduct(productosDevueltos.get(0).getId());
		productoService.deleteProduct(productosDevueltos.get(1).getId());
		productoService.deleteProduct(productosDevueltos.get(2).getId());
		productoService.deleteProduct(productosDevueltos.get(3).getId());
		creaLineas();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	void testBuscaProductoPorOferta() {
		List<Producto> productosDevueltos = crearYGuardarProductos();

		List<Producto> listaProductosDevueltos = productoService.findByOferta();

		log.info("");
		assertEquals(2, listaProductosDevueltos.size());
		assertEquals(productosDevueltos.get(2), listaProductosDevueltos.get(0));
		assertEquals(productosDevueltos.get(3), listaProductosDevueltos.get(1));

		productoService.deleteProduct(productosDevueltos.get(0).getId());
		productoService.deleteProduct(productosDevueltos.get(1).getId());
		productoService.deleteProduct(productosDevueltos.get(2).getId());
		productoService.deleteProduct(productosDevueltos.get(3).getId());
		creaLineas();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	void testBuscaProductoPorNuestros_productos() {
		List<Producto> productosDevueltos = crearYGuardarProductos();

		List<Producto> listaProductosDevueltos = productoService.findByNuestrosProductos();

		log.info("");
		assertEquals(2, listaProductosDevueltos.size());
		assertEquals(productosDevueltos.get(1), listaProductosDevueltos.get(0));
		assertEquals(productosDevueltos.get(3), listaProductosDevueltos.get(1));

		productoService.deleteProduct(productosDevueltos.get(0).getId());
		productoService.deleteProduct(productosDevueltos.get(1).getId());
		productoService.deleteProduct(productosDevueltos.get(2).getId());
		productoService.deleteProduct(productosDevueltos.get(3).getId());
		creaLineas();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	void testActualizaProducto() {
		List<Producto> productosDevueltos = crearYGuardarProductos();

		Producto productoAActualizar = productoService.findById(productosDevueltos.get(0).getId());

		productoAActualizar.setCantidad(9);
		productoAActualizar.setNombre("Fresas 2.0");
		productoAActualizar.setNuestros_productos(true);
		productoAActualizar.setOferta(true);
		productoAActualizar.setPrecio(2.5f);

		productoService.saveProduct(productoAActualizar);

		Producto productoActualizado = productoService.findById(productoAActualizar.getId());
		log.info("");
		assertEquals(9, productoActualizado.getCantidad());
		assertEquals("Fresas 2.0", productoActualizado.getNombre());
		assertEquals(true, productoActualizado.isNuestros_productos());
		assertEquals(true, productoActualizado.isOferta());
		assertEquals(2.5f, productoActualizado.getPrecio());

		productoService.deleteProduct(productosDevueltos.get(0).getId());
		productoService.deleteProduct(productosDevueltos.get(1).getId());
		productoService.deleteProduct(productosDevueltos.get(2).getId());
		productoService.deleteProduct(productosDevueltos.get(3).getId());
		creaLineas();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	void testProductoExceptionSinNombre() {

		Producto productoAActualizar = new Producto();

		productoAActualizar.setCantidad(9);
		productoAActualizar.setNombre(null);
		productoAActualizar.setNuestros_productos(true);
		productoAActualizar.setOferta(true);
		productoAActualizar.setPrecio(2.5f);

		try {
			productoService.saveProduct(productoAActualizar);
			fail();
		} catch (ConstraintViolationException e) {
			final String expected = "Validation failed for classes [com.tfg.entity.Producto] during persist time for groups [javax.validation.groups.Default, ]\n"
					+ "List of constraint violations:[\n"
					+ "	ConstraintViolationImpl{interpolatedMessage='El campo nombre no puede ser nulo', propertyPath=nombre, rootBeanClass=class com.tfg.entity.Producto, messageTemplate='El campo nombre no puede ser nulo'}\n"
					+ "]";
			assertEquals(expected, e.getMessage());
		}

		creaLineas();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	void testProductoExceptionNombreNoValido() {

		Producto productoAActualizar = new Producto();

		productoAActualizar.setCantidad(9);
		productoAActualizar.setNombre("AE");
		productoAActualizar.setNuestros_productos(true);
		productoAActualizar.setOferta(true);
		productoAActualizar.setPrecio(2.5f);

		try {
			productoService.saveProduct(productoAActualizar);
			fail();
		} catch (ConstraintViolationException e) {
			final String expected = "Validation failed for classes [com.tfg.entity.Producto] during persist time for groups [javax.validation.groups.Default, ]\n"
					+ "List of constraint violations:[\n"
					+ "	ConstraintViolationImpl{interpolatedMessage='Nombre debe tener entre 3 y 40 caracteres', propertyPath=nombre, rootBeanClass=class com.tfg.entity.Producto, messageTemplate='Nombre debe tener entre 3 y 40 caracteres'}\n"
					+ "]";
			assertEquals(expected, e.getMessage());
		}

		creaLineas();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	void testProductoExceptionCantidadNegativa() {

		Producto productoAActualizar = new Producto();

		productoAActualizar.setCantidad(-10);
		productoAActualizar.setNombre("Fresas");
		productoAActualizar.setNuestros_productos(true);
		productoAActualizar.setOferta(true);
		productoAActualizar.setPrecio(2.5f);

		try {
			productoService.saveProduct(productoAActualizar);
			fail();
		} catch (ConstraintViolationException e) {
			final String expected = "Validation failed for classes [com.tfg.entity.Producto] during persist time for groups [javax.validation.groups.Default, ]\n"
					+ "List of constraint violations:[\n"
					+ "	ConstraintViolationImpl{interpolatedMessage='El campo cantidad debe ser mayor o igual a 0', propertyPath=cantidad, rootBeanClass=class com.tfg.entity.Producto, messageTemplate='El campo cantidad debe ser mayor o igual a 0'}\n"
					+ "]";
			assertEquals(expected, e.getMessage());
		}

		creaLineas();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	void testProductoExceptionPrecioNoValido() {

		Producto productoAActualizar = new Producto();

		productoAActualizar.setCantidad(9);
		productoAActualizar.setNombre("Fresas");
		productoAActualizar.setNuestros_productos(true);
		productoAActualizar.setOferta(true);
		productoAActualizar.setPrecio(-2.5f);

		try {
			productoService.saveProduct(productoAActualizar);
			fail();
		} catch (ConstraintViolationException e) {
			final String expected = "Validation failed for classes [com.tfg.entity.Producto] during persist time for groups [javax.validation.groups.Default, ]\n" +
					"List of constraint violations:[\n" +
					"\tConstraintViolationImpl{interpolatedMessage='El campo precio debe ser mayor o igual a 0', propertyPath=precio, rootBeanClass=class com.tfg.entity.Producto, messageTemplate='El campo precio debe ser mayor o igual a 0'}\n" +
					"]";
			assertEquals(expected, e.getMessage());
		}

		creaLineas();
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////// ENTIDAD: HISTORICO DE PRECIOS//////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	void testCreaYEliminaHistorico() {
		List<Producto> productosDevueltos = crearYGuardarProductos();

		Producto productoPrueba = productosDevueltos.get(0);

		Historico_precios historico = new Historico_precios(LocalDateTime.now(), productoPrueba,
				productoPrueba.getPrecio());

		Historico_precios historicoDev = historicoService.save(historico);

		log.info("");
		assertEquals(historicoDev, historico);

		historicoService.delete(historicoDev.getId());
		productoService.deleteProduct(productosDevueltos.get(0).getId());
		productoService.deleteProduct(productosDevueltos.get(1).getId());
		productoService.deleteProduct(productosDevueltos.get(2).getId());
		productoService.deleteProduct(productosDevueltos.get(3).getId());
		creaLineas();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	void testbuscaHistoricoPorId() {
		List<Producto> productosDevueltos = crearYGuardarProductos();

		Producto productoPrueba = productosDevueltos.get(0);

		Historico_precios historico = new Historico_precios(LocalDateTime.now(), productoPrueba,
				productoPrueba.getPrecio());
		Historico_precios historico2 = new Historico_precios(LocalDateTime.now(), productosDevueltos.get(1), 100F);
		Historico_precios historico3 = new Historico_precios(LocalDateTime.now(), productosDevueltos.get(2), 200F);

		Historico_precios historicoDev = historicoService.save(historico);
		Historico_precios historicoDev2 = historicoService.save(historico2);
		Historico_precios historicoDev3 = historicoService.save(historico3);

		Historico_precios historicoBuscado2 = historicoService.findById(historicoDev2.getId());

		log.info("");
		assertEquals(historicoDev2.getId(), historicoBuscado2.getId());

		historicoService.delete(historicoDev.getId());
		historicoService.delete(historicoDev2.getId());
		historicoService.delete(historicoDev3.getId());
		productoService.deleteProduct(productosDevueltos.get(0).getId());
		productoService.deleteProduct(productosDevueltos.get(1).getId());
		productoService.deleteProduct(productosDevueltos.get(2).getId());
		productoService.deleteProduct(productosDevueltos.get(3).getId());
		creaLineas();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	void testbuscaHistoricoPorPrecioActual() {
		List<Producto> productosDevueltos = crearYGuardarProductos();

		Producto productoPrueba = productosDevueltos.get(0);

		Historico_precios historico = new Historico_precios(LocalDateTime.now(), productoPrueba,
				productoPrueba.getPrecio());
		historico.setFechaFin(LocalDateTime.now());
		Historico_precios historico2 = new Historico_precios(LocalDateTime.now(), productoPrueba, 100F);
		historico2.setFechaFin(LocalDateTime.now());
		Historico_precios historico3 = new Historico_precios(LocalDateTime.now(), productoPrueba, 200F);

		Historico_precios historicoDev = historicoService.save(historico);
		Historico_precios historicoDev2 = historicoService.save(historico2);
		Historico_precios historicoDev3 = historicoService.save(historico3);

		Historico_precios historicoBuscado = historicoService.findCurrentPrice(productoPrueba.getId());

		log.info("");
		assertEquals(historicoDev3.getId(), historicoBuscado.getId());

		historicoService.delete(historicoDev.getId());
		historicoService.delete(historicoDev2.getId());
		historicoService.delete(historicoDev3.getId());
		productoService.deleteProduct(productosDevueltos.get(0).getId());
		productoService.deleteProduct(productosDevueltos.get(1).getId());
		productoService.deleteProduct(productosDevueltos.get(2).getId());
		productoService.deleteProduct(productosDevueltos.get(3).getId());
		creaLineas();
	}
	
	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	void testBuscaHistoricoPorIdDeProducto() {
		List<Producto> productosDevueltos = crearYGuardarProductos();

		Producto productoPrueba = productosDevueltos.get(0);

		Historico_precios historico = new Historico_precios(LocalDateTime.now(), productoPrueba,
				productoPrueba.getPrecio());
		historico.setFechaFin(LocalDateTime.now());
		Historico_precios historico2 = new Historico_precios(LocalDateTime.now(), productoPrueba, 100F);
		historico2.setFechaFin(LocalDateTime.now());
		Historico_precios historico3 = new Historico_precios(LocalDateTime.now(), productoPrueba, 200F);

		Historico_precios historicoDev = historicoService.save(historico);
		Historico_precios historicoDev2 = historicoService.save(historico2);
		Historico_precios historicoDev3 = historicoService.save(historico3);

		List <Historico_precios> historicoBuscado = historicoService.findByProductId(productoPrueba.getId());

		log.info("");
		assertEquals(historicoDev.getId(), historicoBuscado.get(0).getId());
		assertEquals(historicoDev2.getId(), historicoBuscado.get(1).getId());
		assertEquals(historicoDev3.getId(), historicoBuscado.get(2).getId());

		historicoService.delete(historicoDev.getId());
		historicoService.delete(historicoDev2.getId());
		historicoService.delete(historicoDev3.getId());
		productoService.deleteProduct(productosDevueltos.get(0).getId());
		productoService.deleteProduct(productosDevueltos.get(1).getId());
		productoService.deleteProduct(productosDevueltos.get(2).getId());
		productoService.deleteProduct(productosDevueltos.get(3).getId());
		creaLineas();
	}
	
	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	void testPuntuaProducto() {
		List<Producto> productosDevueltos = crearYGuardarProductos();
		
		
		productoService.rateProduct(5, productosDevueltos.get(2).getId());
		productoService.rateProduct(0, productosDevueltos.get(2).getId());
		
		Producto productoPrueba=productoService.findById(productosDevueltos.get(2).getId());
		
		assertEquals(2,productoPrueba.getNumero_valoraciones());
		assertEquals(2.5F,productoPrueba.getValoracion());

		productoService.deleteProduct(productosDevueltos.get(0).getId());
		productoService.deleteProduct(productosDevueltos.get(1).getId());
		productoService.deleteProduct(productosDevueltos.get(2).getId());
		productoService.deleteProduct(productosDevueltos.get(3).getId());

		creaLineas();
	}
	
	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	void testPuntuaMalProducto() {
		List<Producto> productosDevueltos = crearYGuardarProductos();		
		try {
			productoService.rateProduct(-5, productosDevueltos.get(2).getId());
			fail();
		} catch (ConstraintViolationException e) {
			final String expected = "La nota debe estar entre 0 y 5";
			assertEquals(expected, e.getMessage());
		}

	}
	
	

}
