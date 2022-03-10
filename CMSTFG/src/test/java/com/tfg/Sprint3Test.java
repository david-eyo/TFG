package com.tfg;

import com.tfg.dao.IAdminDao;
import com.tfg.dao.ICarritoDao;
import com.tfg.dao.ITrabajadorDao;
import com.tfg.dao.IUser_generalDao;
import com.tfg.entity.*;
import com.tfg.service.CustomUserDetailService;
import com.tfg.service.ICarritoService;
import com.tfg.service.IHistoricoService;
import com.tfg.service.IProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;

import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class Sprint3Test {

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IHistoricoService historicoService;

    @Autowired
    private CustomUserDetailService userService;

    @Autowired
    private IAdminDao adminRepository;

    @Autowired
    private ITrabajadorDao trabajadorRepository;

    @Autowired
    private IUser_generalDao user_generalRepository;

    @Autowired
    private ICarritoService carritoService;



    private Logger log = Logger.getLogger("Usuarios y carrito");

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

    public void creaLineas() {
        log.info("-------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------");

    }

    public User_general creaUsuarioNormal(){
        Date date = new GregorianCalendar(1999, Calendar.FEBRUARY, 11).getTime();
        User_general user = new User_general();
        user.setUsername("usuarioUsername");
        user.setPassword("usuarioPassword");
        user.setNombre("usuarioNombre");
        user.setApellidos("usuarioApellidos");
        user.setEmail("usuarioEmail");
        user.setCiudad("usuarioCiudad");
        user.setCp("15707");
        user.setDireccion("usuarioDireccion");
        user.setFechaNacimiento(date);
        user.setTlf("usuarioTlf");
        return user;
    }

    public Admin creaAdminNormal(){
        Date date = new GregorianCalendar(1989, Calendar.SEPTEMBER, 17).getTime();
        Admin admin = new Admin();
        admin.setUsername("adminUsername");
        admin.setPassword("adminPassword");
        admin.setNombre("adminNombre");
        admin.setApellidos("adminApellidos");
        admin.setEmail("adminEmail");
        admin.setCiudad("adminCiudad");
        admin.setCp("15707");
        admin.setDireccion("adminDireccion");
        admin.setFechaNacimiento(date);
        admin.setTlf("adminTlfon");
        return admin;
    }

    public Trabajador creaTrabajadorNormal(){
        Date date = new GregorianCalendar(1940, Calendar.JANUARY, 30).getTime();
        Trabajador trabajador = new Trabajador();
        trabajador.setUsername("trabajadorUsername");
        trabajador.setPassword("trabajadorPassword");
        trabajador.setNombre("trabajadorNombre");
        trabajador.setApellidos("trabajadorApellidos");
        trabajador.setEmail("trabajadorEmail");
        trabajador.setCiudad("trabajadorCiudad");
        trabajador.setCp("15707");
        trabajador.setDireccion("trabajadorDireccion");
        trabajador.setFechaNacimiento(date);
        trabajador.setTlf("trabajadorTlf");
        return trabajador;
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


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testCreaUsuario_Normal() {
        creaLineas();

        log.info("Creando Usuario Normal" +
                ":");

        User_general user = creaUsuarioNormal();
        User_general usuario_devuelto=user_generalRepository.save(user);
        assertEquals(user, usuario_devuelto);
        user_generalRepository.delete(usuario_devuelto);
    }

    @Test
    void testCreaYEliminaAdmin() {
        creaLineas();

        log.info("Creando Admin" +
                ":");

        Admin admin = creaAdminNormal();
        Admin administrador=adminRepository.save(admin);
        assertEquals(admin, administrador);
        adminRepository.delete(admin);
    }

    @Test
    void testCreaYEliminaTrabajador() {
        creaLineas();

        log.info("Creando Trabajador" +
                ":");

        Trabajador trabajador = creaTrabajadorNormal();
        Trabajador trabajadorDB=trabajadorRepository.save(trabajador);
        assertEquals(trabajador, trabajadorDB);
        trabajadorRepository.delete(trabajador);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testCreaCarritoUsuarioYBuscaCarritoPorUsuario() {
        creaLineas();

        log.info("Creando los 3 tipos de usuarios" +
                ":");

        Admin admin = creaAdminNormal();
        adminRepository.save(admin);

        Trabajador trabajador = creaTrabajadorNormal();
        trabajadorRepository.save(trabajador);

        User_general user = creaUsuarioNormal();
        user_generalRepository.save(user);

        List<Producto> productosDevueltos = crearYGuardarProductos();

        carritoService.saveProducto(productosDevueltos.get(0).getId(),user.getId(), 10);
        carritoService.saveProducto(productosDevueltos.get(1).getId(),user.getId(), 12);
        carritoService.saveProducto(productosDevueltos.get(2).getId(),user.getId(), 8);
        carritoService.saveProducto(productosDevueltos.get(3).getId(),user.getId(), 20);

        List<Carrito> respuesta = carritoService.findCarritoByUsername(user.getUsername());
        assertEquals(respuesta.size(), 4);

        adminRepository.delete(admin);
        trabajadorRepository.delete(trabajador);
        carritoService.delete(respuesta.get(0).getId());
        carritoService.delete(respuesta.get(1).getId());
        carritoService.delete(respuesta.get(2).getId());
        carritoService.delete(respuesta.get(3).getId());
        user_generalRepository.delete(user);

        productoService.deleteProduct(productosDevueltos.get(0).getId());
        productoService.deleteProduct(productosDevueltos.get(1).getId());
        productoService.deleteProduct(productosDevueltos.get(2).getId());
        productoService.deleteProduct(productosDevueltos.get(3).getId());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testProductoYaEnCarrito() {
        creaLineas();

        log.info("Creando los 3 tipos de usuarios" +
                ":");

        Admin admin = creaAdminNormal();
        adminRepository.save(admin);

        Trabajador trabajador = creaTrabajadorNormal();
        trabajadorRepository.save(trabajador);

        User_general user = creaUsuarioNormal();
        user_generalRepository.save(user);

        List<Producto> productosDevueltos = crearYGuardarProductos();

        Boolean antes = carritoService.productoYaEnCarrito(productosDevueltos.get(0).getId(),user.getId());
        carritoService.saveProducto(productosDevueltos.get(0).getId(),user.getId(), 10);
        carritoService.saveProducto(productosDevueltos.get(1).getId(),user.getId(), 12);
        carritoService.saveProducto(productosDevueltos.get(2).getId(),user.getId(), 8);
        carritoService.saveProducto(productosDevueltos.get(3).getId(),user.getId(), 20);
        List<Carrito> respuesta = carritoService.findCarritoByUsername(user.getUsername());

        Boolean despues = carritoService.productoYaEnCarrito(productosDevueltos.get(0).getId(),user.getId());

        assertEquals(false, antes);
        assertEquals(true, despues);


        adminRepository.delete(admin);
        trabajadorRepository.delete(trabajador);
        carritoService.delete(respuesta.get(0).getId());
        carritoService.delete(respuesta.get(1).getId());
        carritoService.delete(respuesta.get(2).getId());
        carritoService.delete(respuesta.get(3).getId());
        user_generalRepository.delete(user);

        productoService.deleteProduct(productosDevueltos.get(0).getId());
        productoService.deleteProduct(productosDevueltos.get(1).getId());
        productoService.deleteProduct(productosDevueltos.get(2).getId());
        productoService.deleteProduct(productosDevueltos.get(3).getId());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testBuscarCarritoPorId() {
        creaLineas();

        log.info("Creando los 3 tipos de usuarios" +
                ":");

        Admin admin = creaAdminNormal();
        adminRepository.save(admin);

        Trabajador trabajador = creaTrabajadorNormal();
        trabajadorRepository.save(trabajador);

        User_general user = creaUsuarioNormal();
        user_generalRepository.save(user);

        List<Producto> productosDevueltos = crearYGuardarProductos();

        Boolean antes = carritoService.productoYaEnCarrito(productosDevueltos.get(0).getId(),user.getId());
        carritoService.saveProducto(productosDevueltos.get(0).getId(),user.getId(), 10);
        carritoService.saveProducto(productosDevueltos.get(1).getId(),user.getId(), 12);
        carritoService.saveProducto(productosDevueltos.get(2).getId(),user.getId(), 8);
        carritoService.saveProducto(productosDevueltos.get(3).getId(),user.getId(), 20);
        List <Carrito> respuesta = carritoService.findCarritoByUsername(user.getUsername());

        adminRepository.delete(admin);
        trabajadorRepository.delete(trabajador);
        carritoService.delete(respuesta.get(0).getId());
        carritoService.delete(respuesta.get(1).getId());
        carritoService.delete(respuesta.get(2).getId());
        carritoService.delete(respuesta.get(3).getId());
        user_generalRepository.delete(user);

        productoService.deleteProduct(productosDevueltos.get(0).getId());
        productoService.deleteProduct(productosDevueltos.get(1).getId());
        productoService.deleteProduct(productosDevueltos.get(2).getId());
        productoService.deleteProduct(productosDevueltos.get(3).getId());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testBuscarUsuarioporId() {
        creaLineas();

        log.info("Creando los 3 tipos de usuarios" +
                ":");

        Admin admin = creaAdminNormal();
        adminRepository.save(admin);

        Trabajador trabajador = creaTrabajadorNormal();
        trabajadorRepository.save(trabajador);

        User_general user = creaUsuarioNormal();
        user_generalRepository.save(user);

        List<Producto> productosDevueltos = crearYGuardarProductos();

        UserDetails usuario = userService.loadUserById(user.getId());

        assertEquals(usuario.getUsername(),user.getUsername());



        adminRepository.delete(admin);
        trabajadorRepository.delete(trabajador);
        user_generalRepository.delete(user);

        productoService.deleteProduct(productosDevueltos.get(0).getId());
        productoService.deleteProduct(productosDevueltos.get(1).getId());
        productoService.deleteProduct(productosDevueltos.get(2).getId());
        productoService.deleteProduct(productosDevueltos.get(3).getId());

    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testBuscarAdminporNombre() {
        creaLineas();

        log.info("Creando los 3 tipos de usuarios" +
                ":");

        Admin admin = creaAdminNormal();
        adminRepository.save(admin);

        Trabajador trabajador = creaTrabajadorNormal();
        trabajadorRepository.save(trabajador);

        User_general user = creaUsuarioNormal();
        user_generalRepository.save(user);

        List<Producto> productosDevueltos = crearYGuardarProductos();

        UserDetails usuario = userService.loadUserByUsername(user.getUsername());

        assertEquals(usuario.getUsername(),user.getUsername());



        adminRepository.delete(admin);
        trabajadorRepository.delete(trabajador);
        user_generalRepository.delete(user);

        productoService.deleteProduct(productosDevueltos.get(0).getId());
        productoService.deleteProduct(productosDevueltos.get(1).getId());
        productoService.deleteProduct(productosDevueltos.get(2).getId());
        productoService.deleteProduct(productosDevueltos.get(3).getId());

    }




}
