package com.tfg;

import com.tfg.dao.IAdminDao;
import com.tfg.dao.ITrabajadorDao;
import com.tfg.dao.IUser_generalDao;
import com.tfg.entity.*;
import com.tfg.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class Sprint5Test {

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

    @Autowired
    private IPedidoService pedidoService;

    @Autowired
    private Iitem_pedidoService itemService;




    private Logger log = Logger.getLogger("Usuarios y carrito");

    private Producto creaProducto1() {
        Producto producto = new Producto();
        producto.setNombre("Fresas");
        producto.setPrecio(1.5f);
        producto.setCantidad(100);
        producto.setNuestros_productos(false);
        producto.setOferta(false);
        return producto;
    }

    private Producto creaProducto2() {
        Producto producto = new Producto();
        producto.setNombre("Mandarina");
        producto.setPrecio(1.33f);
        producto.setCantidad(100);
        producto.setNuestros_productos(true);
        producto.setOferta(false);
        return producto;
    }

    private Producto creaProducto3() {
        Producto producto = new Producto();
        producto.setNombre("Pomelo");
        producto.setPrecio(2.5f);
        producto.setCantidad(100);
        producto.setNuestros_productos(false);
        producto.setOferta(true);
        return producto;
    }

    private Producto creaProducto4() {
        Producto producto = new Producto();
        producto.setNombre("Limon");
        producto.setPrecio(0.99f);
        producto.setCantidad(100);
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
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testCreaCarritoUsuarioYBuscaCarritoPorUsuario() {
        creaLineas();
        //Crea los usuarios (administrador y usuario)
        Admin admin = creaAdminNormal();
        adminRepository.save(admin);
        User_general user = creaUsuarioNormal();
        user_generalRepository.save(user);

        //Creamos los productos y guardamos sus precios en su respectivo historico de precios
        List<Producto> productosDevueltos = crearYGuardarProductos();
        List<Historico_precios> historicos = new ArrayList<>();
        for (int j = 0; j<productosDevueltos.size(); j++){
            Historico_precios historico1 = new Historico_precios(LocalDateTime.now(), productosDevueltos.get(0), productosDevueltos.get(0).getPrecio());
            Historico_precios historicoDB1 = historicoService.save(historico1);
            historicos.add(historicoDB1);
        }

        //Guardamos los productos en el carrito del usuario con su cantidad
        carritoService.saveProducto(productosDevueltos.get(0).getId(),user.getId(), 10);
        carritoService.saveProducto(productosDevueltos.get(1).getId(),user.getId(), 12);
        carritoService.saveProducto(productosDevueltos.get(2).getId(),user.getId(), 8);

        //Realizamos el pedido
        Pedido pedido = new Pedido();
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setUsuario(user);
        List<Carrito> carritodelusuario = carritoService.findCarritoByUsername(user.getUsername());
        List<item_pedido> listaitems = new ArrayList<>();

        List <Historico_precios> conjuntoprecios= new ArrayList<>();
        for (int i=0; i<carritodelusuario.size(); i++){
            Producto producto=carritodelusuario.get(i).getProductos();
            int cantidad = carritodelusuario.get(i).getCantidad();
            conjuntoprecios= historicoService.findByProductId(producto.getId());
            //Al estar trabajando directamente con los servicios es necesario que ponga esto aqui,
            //ya que sino, el al crear solamente el producto, el historico no se crea directamente,
            //para nuestra aplicacion eso lo hacemos en el controller.
            Historico_precios precio = conjuntoprecios.get(conjuntoprecios.size()-1);
            item_pedido item = new item_pedido();
            item.setCantidad(cantidad);
            item.setPrecio(precio);
            item.setProducto(producto);
            item_pedido itemPersistido=itemService.save(item);
            producto.setCantidad(producto.getCantidad()-cantidad);
            productoService.saveProduct(producto);
            listaitems.add(itemPersistido);
            for (i=0; i<carritodelusuario.size(); i++){
                carritoService.delete(carritodelusuario.get(i).getId());
            }
        }
        pedido.setItem_pedido(listaitems);
        pedido.setEstado(Pedido.Estado_envio.NOTIFICADO);
        Pedido pedidoentregado = pedidoService.save(pedido);
        Pedido pedidodevuelto = pedidoService.findPedidoById(pedidoentregado.getId());
        assertEquals(pedido.getId(), pedidoentregado.getId());

        //Proceso de eliminacion de objetos necesarios
        pedidoService.delete(pedidodevuelto.getId());
        for (int i = 0; i<listaitems.size(); i++){
            itemService.delete(listaitems.get(i).getId());
        }
        adminRepository.delete(admin);
        user_generalRepository.delete(user);
        for (int i=0; i<historicos.size(); i++){
            historicoService.delete(historicos.get(i).getId());
        }
        productoService.deleteProduct(productosDevueltos.get(0).getId());
        productoService.deleteProduct(productosDevueltos.get(1).getId());
        productoService.deleteProduct(productosDevueltos.get(2).getId());
        productoService.deleteProduct(productosDevueltos.get(3).getId());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testBuscaPedidoPorUsuario() {
        creaLineas();
        //Crea los usuarios (administrador y usuario)
        Admin admin = creaAdminNormal();
        adminRepository.save(admin);
        User_general user = creaUsuarioNormal();
        user_generalRepository.save(user);

        //Creamos los productos y guardamos sus precios en su respectivo historico de precios
        List<Producto> productosDevueltos = crearYGuardarProductos();
        List<Historico_precios> historicos = new ArrayList<>();
        for (int j = 0; j<productosDevueltos.size(); j++){
            Historico_precios historico1 = new Historico_precios(LocalDateTime.now(), productosDevueltos.get(0), productosDevueltos.get(0).getPrecio());
            Historico_precios historicoDB1 = historicoService.save(historico1);
            historicos.add(historicoDB1);
        }

        //Guardamos los productos en el carrito del usuario con su cantidad
        carritoService.saveProducto(productosDevueltos.get(0).getId(),user.getId(), 10);
        carritoService.saveProducto(productosDevueltos.get(1).getId(),user.getId(), 12);
        carritoService.saveProducto(productosDevueltos.get(2).getId(),user.getId(), 8);

        //Realizamos el pedido
        Pedido pedido = new Pedido();
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setUsuario(user);
        List<Carrito> carritodelusuario = carritoService.findCarritoByUsername(user.getUsername());
        List<item_pedido> listaitems = new ArrayList<>();

        List <Historico_precios> conjuntoprecios= new ArrayList<>();
        for (int i=0; i<carritodelusuario.size(); i++){
            Producto producto=carritodelusuario.get(i).getProductos();
            int cantidad = carritodelusuario.get(i).getCantidad();
            conjuntoprecios= historicoService.findByProductId(producto.getId());
            //Al estar trabajando directamente con los servicios es necesario que ponga esto aqui,
            //ya que sino, el al crear solamente el producto, el historico no se crea directamente,
            //para nuestra aplicacion eso lo hacemos en el controller.
            Historico_precios precio = conjuntoprecios.get(conjuntoprecios.size()-1);
            item_pedido item = new item_pedido();
            item.setCantidad(cantidad);
            item.setPrecio(precio);
            item.setProducto(producto);
            item_pedido itemPersistido=itemService.save(item);
            producto.setCantidad(producto.getCantidad()-cantidad);
            productoService.saveProduct(producto);
            listaitems.add(itemPersistido);
            for (i=0; i<carritodelusuario.size(); i++){
                carritoService.delete(carritodelusuario.get(i).getId());
            }
        }
        pedido.setItem_pedido(listaitems);
        pedido.setEstado(Pedido.Estado_envio.NOTIFICADO);
        Pedido pedidoentregado = pedidoService.save(pedido);
        List<Pedido> pedidodevuelto = pedidoService.findPedidoByUsername(user.getUsername());
        assertEquals(pedidoentregado.getId(), pedidodevuelto.get(0).getId());



        //Proceso de eliminacion de objetos necesarios
        pedidoService.delete(pedidoentregado.getId());
        for (int i = 0; i<listaitems.size(); i++){
            itemService.delete(listaitems.get(i).getId());
        }
        adminRepository.delete(admin);
        user_generalRepository.delete(user);
        for (int i=0; i<historicos.size(); i++){
            historicoService.delete(historicos.get(i).getId());
        }
        productoService.deleteProduct(productosDevueltos.get(0).getId());
        productoService.deleteProduct(productosDevueltos.get(1).getId());
        productoService.deleteProduct(productosDevueltos.get(2).getId());
        productoService.deleteProduct(productosDevueltos.get(3).getId());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testBuscaPedidoPorEstadoNotificado() {
        creaLineas();
        //Crea los usuarios (administrador y usuario)
        Admin admin = creaAdminNormal();
        adminRepository.save(admin);
        User_general user = creaUsuarioNormal();
        user_generalRepository.save(user);

        //Creamos los productos y guardamos sus precios en su respectivo historico de precios
        List<Producto> productosDevueltos = crearYGuardarProductos();
        List<Historico_precios> historicos = new ArrayList<>();
        for (int j = 0; j<productosDevueltos.size(); j++){
            Historico_precios historico1 = new Historico_precios(LocalDateTime.now(), productosDevueltos.get(0), productosDevueltos.get(0).getPrecio());
            Historico_precios historicoDB1 = historicoService.save(historico1);
            historicos.add(historicoDB1);
        }

        //Guardamos los productos en el carrito del usuario con su cantidad
        carritoService.saveProducto(productosDevueltos.get(0).getId(),user.getId(), 10);
        carritoService.saveProducto(productosDevueltos.get(1).getId(),user.getId(), 12);
        carritoService.saveProducto(productosDevueltos.get(2).getId(),user.getId(), 8);

        //Realizamos el pedido
        Pedido pedido = new Pedido();
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setUsuario(user);
        List<Carrito> carritodelusuario = carritoService.findCarritoByUsername(user.getUsername());
        List<item_pedido> listaitems = new ArrayList<>();

        List <Historico_precios> conjuntoprecios= new ArrayList<>();
        for (int i=0; i<carritodelusuario.size(); i++){
            Producto producto=carritodelusuario.get(i).getProductos();
            int cantidad = carritodelusuario.get(i).getCantidad();
            conjuntoprecios= historicoService.findByProductId(producto.getId());
            //Al estar trabajando directamente con los servicios es necesario que ponga esto aqui,
            //ya que sino, el al crear solamente el producto, el historico no se crea directamente,
            //para nuestra aplicacion eso lo hacemos en el controller.
            Historico_precios precio = conjuntoprecios.get(conjuntoprecios.size()-1);
            item_pedido item = new item_pedido();
            item.setCantidad(cantidad);
            item.setPrecio(precio);
            item.setProducto(producto);
            item_pedido itemPersistido=itemService.save(item);
            producto.setCantidad(producto.getCantidad()-cantidad);
            productoService.saveProduct(producto);
            listaitems.add(itemPersistido);
            for (i=0; i<carritodelusuario.size(); i++){
                carritoService.delete(carritodelusuario.get(i).getId());
            }
        }
        pedido.setItem_pedido(listaitems);
        pedido.setEstado(Pedido.Estado_envio.NOTIFICADO);
        Pedido pedidoentregado = pedidoService.save(pedido);
        List<Pedido> pedidodevuelto = pedidoService.findPedidoByEstado(Pedido.Estado_envio.NOTIFICADO);
        assertEquals(pedidoentregado.getId(), pedidodevuelto.get(0).getId());



        //Proceso de eliminacion de objetos necesarios
        pedidoService.delete(pedidoentregado.getId());
        for (int i = 0; i<listaitems.size(); i++){
            itemService.delete(listaitems.get(i).getId());
        }
        adminRepository.delete(admin);
        user_generalRepository.delete(user);
        for (int i=0; i<historicos.size(); i++){
            historicoService.delete(historicos.get(i).getId());
        }
        productoService.deleteProduct(productosDevueltos.get(0).getId());
        productoService.deleteProduct(productosDevueltos.get(1).getId());
        productoService.deleteProduct(productosDevueltos.get(2).getId());
        productoService.deleteProduct(productosDevueltos.get(3).getId());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testBuscaPedidoPorEstadoEnviado() {
        creaLineas();
        //Crea los usuarios (administrador y usuario)
        Admin admin = creaAdminNormal();
        adminRepository.save(admin);
        User_general user = creaUsuarioNormal();
        user_generalRepository.save(user);

        //Creamos los productos y guardamos sus precios en su respectivo historico de precios
        List<Producto> productosDevueltos = crearYGuardarProductos();
        List<Historico_precios> historicos = new ArrayList<>();
        for (int j = 0; j<productosDevueltos.size(); j++){
            Historico_precios historico1 = new Historico_precios(LocalDateTime.now(), productosDevueltos.get(0), productosDevueltos.get(0).getPrecio());
            Historico_precios historicoDB1 = historicoService.save(historico1);
            historicos.add(historicoDB1);
        }

        //Guardamos los productos en el carrito del usuario con su cantidad
        carritoService.saveProducto(productosDevueltos.get(0).getId(),user.getId(), 10);
        carritoService.saveProducto(productosDevueltos.get(1).getId(),user.getId(), 12);
        carritoService.saveProducto(productosDevueltos.get(2).getId(),user.getId(), 8);

        //Realizamos el pedido
        Pedido pedido = new Pedido();
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setUsuario(user);
        List<Carrito> carritodelusuario = carritoService.findCarritoByUsername(user.getUsername());
        List<item_pedido> listaitems = new ArrayList<>();

        List <Historico_precios> conjuntoprecios= new ArrayList<>();
        for (int i=0; i<carritodelusuario.size(); i++){
            Producto producto=carritodelusuario.get(i).getProductos();
            int cantidad = carritodelusuario.get(i).getCantidad();
            conjuntoprecios= historicoService.findByProductId(producto.getId());
            //Al estar trabajando directamente con los servicios es necesario que ponga esto aqui,
            //ya que sino, el al crear solamente el producto, el historico no se crea directamente,
            //para nuestra aplicacion eso lo hacemos en el controller.
            Historico_precios precio = conjuntoprecios.get(conjuntoprecios.size()-1);
            item_pedido item = new item_pedido();
            item.setCantidad(cantidad);
            item.setPrecio(precio);
            item.setProducto(producto);
            item_pedido itemPersistido=itemService.save(item);
            producto.setCantidad(producto.getCantidad()-cantidad);
            productoService.saveProduct(producto);
            listaitems.add(itemPersistido);
            for (i=0; i<carritodelusuario.size(); i++){
                carritoService.delete(carritodelusuario.get(i).getId());
            }
        }
        pedido.setItem_pedido(listaitems);
        pedido.setEstado(Pedido.Estado_envio.NOTIFICADO);
        Pedido pedidoentregado = pedidoService.save(pedido);
        List<Pedido> pedidodevuelto = pedidoService.findPedidoByEstado(Pedido.Estado_envio.ENVIADO);
        assertEquals(0, pedidodevuelto.size());



        //Proceso de eliminacion de objetos necesarios
        pedidoService.delete(pedidoentregado.getId());
        for (int i = 0; i<listaitems.size(); i++){
            itemService.delete(listaitems.get(i).getId());
        }
        adminRepository.delete(admin);
        user_generalRepository.delete(user);
        for (int i=0; i<historicos.size(); i++){
            historicoService.delete(historicos.get(i).getId());
        }
        productoService.deleteProduct(productosDevueltos.get(0).getId());
        productoService.deleteProduct(productosDevueltos.get(1).getId());
        productoService.deleteProduct(productosDevueltos.get(2).getId());
        productoService.deleteProduct(productosDevueltos.get(3).getId());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testBuscaPedidoPorEstadoyUsuario() {
        creaLineas();
        //Crea los usuarios (administrador y usuario)
        Admin admin = creaAdminNormal();
        adminRepository.save(admin);
        User_general user = creaUsuarioNormal();
        user_generalRepository.save(user);

        //Creamos los productos y guardamos sus precios en su respectivo historico de precios
        List<Producto> productosDevueltos = crearYGuardarProductos();
        List<Historico_precios> historicos = new ArrayList<>();
        for (int j = 0; j<productosDevueltos.size(); j++){
            Historico_precios historico1 = new Historico_precios(LocalDateTime.now(), productosDevueltos.get(0), productosDevueltos.get(0).getPrecio());
            Historico_precios historicoDB1 = historicoService.save(historico1);
            historicos.add(historicoDB1);
        }

        //Guardamos los productos en el carrito del usuario con su cantidad
        carritoService.saveProducto(productosDevueltos.get(0).getId(),user.getId(), 10);
        carritoService.saveProducto(productosDevueltos.get(1).getId(),user.getId(), 12);
        carritoService.saveProducto(productosDevueltos.get(2).getId(),user.getId(), 8);

        //Realizamos el pedido
        Pedido pedido = new Pedido();
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setUsuario(user);
        List<Carrito> carritodelusuario = carritoService.findCarritoByUsername(user.getUsername());
        List<item_pedido> listaitems = new ArrayList<>();

        List <Historico_precios> conjuntoprecios= new ArrayList<>();
        for (int i=0; i<carritodelusuario.size(); i++){
            Producto producto=carritodelusuario.get(i).getProductos();
            int cantidad = carritodelusuario.get(i).getCantidad();
            conjuntoprecios= historicoService.findByProductId(producto.getId());
            //Al estar trabajando directamente con los servicios es necesario que ponga esto aqui,
            //ya que sino, el al crear solamente el producto, el historico no se crea directamente,
            //para nuestra aplicacion eso lo hacemos en el controller.
            Historico_precios precio = conjuntoprecios.get(conjuntoprecios.size()-1);
            item_pedido item = new item_pedido();
            item.setCantidad(cantidad);
            item.setPrecio(precio);
            item.setProducto(producto);
            item_pedido itemPersistido=itemService.save(item);
            producto.setCantidad(producto.getCantidad()-cantidad);
            productoService.saveProduct(producto);
            listaitems.add(itemPersistido);
            for (i=0; i<carritodelusuario.size(); i++){
                carritoService.delete(carritodelusuario.get(i).getId());
            }
        }
        pedido.setItem_pedido(listaitems);
        pedido.setEstado(Pedido.Estado_envio.NOTIFICADO);
        Pedido pedidoentregado = pedidoService.save(pedido);
        List<Pedido> pedidodevuelto = pedidoService.findPedidoByUsernameyEstado(user.getUsername(),Pedido.Estado_envio.NOTIFICADO);
        assertEquals(pedidoentregado.getId(), pedidodevuelto.get(0).getId());



        //Proceso de eliminacion de objetos necesarios
        pedidoService.delete(pedidoentregado.getId());
        for (int i = 0; i<listaitems.size(); i++){
            itemService.delete(listaitems.get(i).getId());
        }
        adminRepository.delete(admin);
        user_generalRepository.delete(user);
        for (int i=0; i<historicos.size(); i++){
            historicoService.delete(historicos.get(i).getId());
        }
        productoService.deleteProduct(productosDevueltos.get(0).getId());
        productoService.deleteProduct(productosDevueltos.get(1).getId());
        productoService.deleteProduct(productosDevueltos.get(2).getId());
        productoService.deleteProduct(productosDevueltos.get(3).getId());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testBuscaPedidoPorUsuarioyFechas() {
        creaLineas();
        //Crea los usuarios (administrador y usuario)
        Admin admin = creaAdminNormal();
        adminRepository.save(admin);
        User_general user = creaUsuarioNormal();
        user_generalRepository.save(user);

        //Creamos los productos y guardamos sus precios en su respectivo historico de precios
        List<Producto> productosDevueltos = crearYGuardarProductos();
        List<Historico_precios> historicos = new ArrayList<>();
        for (int j = 0; j<productosDevueltos.size(); j++){
            Historico_precios historico1 = new Historico_precios(LocalDateTime.now(), productosDevueltos.get(0), productosDevueltos.get(0).getPrecio());
            Historico_precios historicoDB1 = historicoService.save(historico1);
            historicos.add(historicoDB1);
        }

        //Guardamos los productos en el carrito del usuario con su cantidad
        carritoService.saveProducto(productosDevueltos.get(0).getId(),user.getId(), 10);
        carritoService.saveProducto(productosDevueltos.get(1).getId(),user.getId(), 12);
        carritoService.saveProducto(productosDevueltos.get(2).getId(),user.getId(), 8);

        //Realizamos el pedido
        Pedido pedido = new Pedido();
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setUsuario(user);
        List<Carrito> carritodelusuario = carritoService.findCarritoByUsername(user.getUsername());
        List<item_pedido> listaitems = new ArrayList<>();

        List <Historico_precios> conjuntoprecios= new ArrayList<>();
        for (int i=0; i<carritodelusuario.size(); i++){
            Producto producto=carritodelusuario.get(i).getProductos();
            int cantidad = carritodelusuario.get(i).getCantidad();
            conjuntoprecios= historicoService.findByProductId(producto.getId());
            //Al estar trabajando directamente con los servicios es necesario que ponga esto aqui,
            //ya que sino, el al crear solamente el producto, el historico no se crea directamente,
            //para nuestra aplicacion eso lo hacemos en el controller.
            Historico_precios precio = conjuntoprecios.get(conjuntoprecios.size()-1);
            item_pedido item = new item_pedido();
            item.setCantidad(cantidad);
            item.setPrecio(precio);
            item.setProducto(producto);
            item_pedido itemPersistido=itemService.save(item);
            producto.setCantidad(producto.getCantidad()-cantidad);
            productoService.saveProduct(producto);
            listaitems.add(itemPersistido);
            for (i=0; i<carritodelusuario.size(); i++){
                carritoService.delete(carritodelusuario.get(i).getId());
            }
        }
        pedido.setItem_pedido(listaitems);
        pedido.setEstado(Pedido.Estado_envio.NOTIFICADO);
        Pedido pedidoentregado = pedidoService.save(pedido);
        LocalDateTime fecha1 = LocalDateTime.of(2015,
                Month.JULY, 29, 19, 30, 40);
        LocalDateTime fecha2 = LocalDateTime.of(2022,
                Month.JULY, 29, 19, 30, 40);
        List<Pedido> pedidodevuelto = pedidoService.findPedidoByUsernameyFechas(user.getUsername(),fecha1, fecha2);
        assertEquals(pedidoentregado.getId(), pedidodevuelto.get(0).getId());



        //Proceso de eliminacion de objetos necesarios
        pedidoService.delete(pedidoentregado.getId());
        for (int i = 0; i<listaitems.size(); i++){
            itemService.delete(listaitems.get(i).getId());
        }
        adminRepository.delete(admin);
        user_generalRepository.delete(user);
        for (int i=0; i<historicos.size(); i++){
            historicoService.delete(historicos.get(i).getId());
        }
        productoService.deleteProduct(productosDevueltos.get(0).getId());
        productoService.deleteProduct(productosDevueltos.get(1).getId());
        productoService.deleteProduct(productosDevueltos.get(2).getId());
        productoService.deleteProduct(productosDevueltos.get(3).getId());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testBuscaPedidoPorEstadoyFechas() {
        creaLineas();
        //Crea los usuarios (administrador y usuario)
        Admin admin = creaAdminNormal();
        adminRepository.save(admin);
        User_general user = creaUsuarioNormal();
        user_generalRepository.save(user);

        //Creamos los productos y guardamos sus precios en su respectivo historico de precios
        List<Producto> productosDevueltos = crearYGuardarProductos();
        List<Historico_precios> historicos = new ArrayList<>();
        for (int j = 0; j<productosDevueltos.size(); j++){
            Historico_precios historico1 = new Historico_precios(LocalDateTime.now(), productosDevueltos.get(0), productosDevueltos.get(0).getPrecio());
            Historico_precios historicoDB1 = historicoService.save(historico1);
            historicos.add(historicoDB1);
        }

        //Guardamos los productos en el carrito del usuario con su cantidad
        carritoService.saveProducto(productosDevueltos.get(0).getId(),user.getId(), 10);
        carritoService.saveProducto(productosDevueltos.get(1).getId(),user.getId(), 12);
        carritoService.saveProducto(productosDevueltos.get(2).getId(),user.getId(), 8);

        //Realizamos el pedido
        Pedido pedido = new Pedido();
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setUsuario(user);
        List<Carrito> carritodelusuario = carritoService.findCarritoByUsername(user.getUsername());
        List<item_pedido> listaitems = new ArrayList<>();

        List <Historico_precios> conjuntoprecios= new ArrayList<>();
        for (int i=0; i<carritodelusuario.size(); i++){
            Producto producto=carritodelusuario.get(i).getProductos();
            int cantidad = carritodelusuario.get(i).getCantidad();
            conjuntoprecios= historicoService.findByProductId(producto.getId());
            //Al estar trabajando directamente con los servicios es necesario que ponga esto aqui,
            //ya que sino, el al crear solamente el producto, el historico no se crea directamente,
            //para nuestra aplicacion eso lo hacemos en el controller.
            Historico_precios precio = conjuntoprecios.get(conjuntoprecios.size()-1);
            item_pedido item = new item_pedido();
            item.setCantidad(cantidad);
            item.setPrecio(precio);
            item.setProducto(producto);
            item_pedido itemPersistido=itemService.save(item);
            producto.setCantidad(producto.getCantidad()-cantidad);
            productoService.saveProduct(producto);
            listaitems.add(itemPersistido);
            for (i=0; i<carritodelusuario.size(); i++){
                carritoService.delete(carritodelusuario.get(i).getId());
            }
        }
        pedido.setItem_pedido(listaitems);
        pedido.setEstado(Pedido.Estado_envio.NOTIFICADO);
        Pedido pedidoentregado = pedidoService.save(pedido);
        LocalDateTime fecha1 = LocalDateTime.of(2015,
                Month.JULY, 29, 19, 30, 40);
        LocalDateTime fecha2 = LocalDateTime.of(2022,
                Month.JULY, 29, 19, 30, 40);
        List<Pedido> pedidodevuelto = pedidoService.findPedidoByEstadoyFechas(Pedido.Estado_envio.NOTIFICADO,fecha1, fecha2);
        assertEquals(pedidoentregado.getId(), pedidodevuelto.get(0).getId());



        //Proceso de eliminacion de objetos necesarios
        pedidoService.delete(pedidoentregado.getId());
        for (int i = 0; i<listaitems.size(); i++){
            itemService.delete(listaitems.get(i).getId());
        }
        adminRepository.delete(admin);
        user_generalRepository.delete(user);
        for (int i=0; i<historicos.size(); i++){
            historicoService.delete(historicos.get(i).getId());
        }
        productoService.deleteProduct(productosDevueltos.get(0).getId());
        productoService.deleteProduct(productosDevueltos.get(1).getId());
        productoService.deleteProduct(productosDevueltos.get(2).getId());
        productoService.deleteProduct(productosDevueltos.get(3).getId());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testBuscaPedidoPorUsernameEstadoyFechas() {
        creaLineas();
        //Crea los usuarios (administrador y usuario)
        Admin admin = creaAdminNormal();
        adminRepository.save(admin);
        User_general user = creaUsuarioNormal();
        user_generalRepository.save(user);

        //Creamos los productos y guardamos sus precios en su respectivo historico de precios
        List<Producto> productosDevueltos = crearYGuardarProductos();
        List<Historico_precios> historicos = new ArrayList<>();
        for (int j = 0; j<productosDevueltos.size(); j++){
            Historico_precios historico1 = new Historico_precios(LocalDateTime.now(), productosDevueltos.get(0), productosDevueltos.get(0).getPrecio());
            Historico_precios historicoDB1 = historicoService.save(historico1);
            historicos.add(historicoDB1);
        }

        //Guardamos los productos en el carrito del usuario con su cantidad
        carritoService.saveProducto(productosDevueltos.get(0).getId(),user.getId(), 10);
        carritoService.saveProducto(productosDevueltos.get(1).getId(),user.getId(), 12);
        carritoService.saveProducto(productosDevueltos.get(2).getId(),user.getId(), 8);

        //Realizamos el pedido
        Pedido pedido = new Pedido();
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setUsuario(user);
        List<Carrito> carritodelusuario = carritoService.findCarritoByUsername(user.getUsername());
        List<item_pedido> listaitems = new ArrayList<>();

        List <Historico_precios> conjuntoprecios= new ArrayList<>();
        for (int i=0; i<carritodelusuario.size(); i++){
            Producto producto=carritodelusuario.get(i).getProductos();
            int cantidad = carritodelusuario.get(i).getCantidad();
            conjuntoprecios= historicoService.findByProductId(producto.getId());
            //Al estar trabajando directamente con los servicios es necesario que ponga esto aqui,
            //ya que sino, el al crear solamente el producto, el historico no se crea directamente,
            //para nuestra aplicacion eso lo hacemos en el controller.
            Historico_precios precio = conjuntoprecios.get(conjuntoprecios.size()-1);
            item_pedido item = new item_pedido();
            item.setCantidad(cantidad);
            item.setPrecio(precio);
            item.setProducto(producto);
            item_pedido itemPersistido=itemService.save(item);
            producto.setCantidad(producto.getCantidad()-cantidad);
            productoService.saveProduct(producto);
            listaitems.add(itemPersistido);
            for (i=0; i<carritodelusuario.size(); i++){
                carritoService.delete(carritodelusuario.get(i).getId());
            }
        }
        pedido.setItem_pedido(listaitems);
        pedido.setEstado(Pedido.Estado_envio.NOTIFICADO);
        Pedido pedidoentregado = pedidoService.save(pedido);
        LocalDateTime fecha1 = LocalDateTime.of(2015,
                Month.JULY, 29, 19, 30, 40);
        LocalDateTime fecha2 = LocalDateTime.of(2022,
                Month.JULY, 29, 19, 30, 40);
        List<Pedido> pedidodevuelto = pedidoService.findPedidoByUsernameEstadoyFechas(user.getUsername(),Pedido.Estado_envio.NOTIFICADO,fecha1, fecha2);
        assertEquals(pedidoentregado.getId(), pedidodevuelto.get(0).getId());



        //Proceso de eliminacion de objetos necesarios
        pedidoService.delete(pedidoentregado.getId());
        for (int i = 0; i<listaitems.size(); i++){
            itemService.delete(listaitems.get(i).getId());
        }
        adminRepository.delete(admin);
        user_generalRepository.delete(user);
        for (int i=0; i<historicos.size(); i++){
            historicoService.delete(historicos.get(i).getId());
        }
        productoService.deleteProduct(productosDevueltos.get(0).getId());
        productoService.deleteProduct(productosDevueltos.get(1).getId());
        productoService.deleteProduct(productosDevueltos.get(2).getId());
        productoService.deleteProduct(productosDevueltos.get(3).getId());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testBuscaPedidoPorUsernameEstadoyFechasMal() {
        creaLineas();
        //Crea los usuarios (administrador y usuario)
        Admin admin = creaAdminNormal();
        adminRepository.save(admin);
        User_general user = creaUsuarioNormal();
        user_generalRepository.save(user);

        //Creamos los productos y guardamos sus precios en su respectivo historico de precios
        List<Producto> productosDevueltos = crearYGuardarProductos();
        List<Historico_precios> historicos = new ArrayList<>();
        for (int j = 0; j<productosDevueltos.size(); j++){
            Historico_precios historico1 = new Historico_precios(LocalDateTime.now(), productosDevueltos.get(0), productosDevueltos.get(0).getPrecio());
            Historico_precios historicoDB1 = historicoService.save(historico1);
            historicos.add(historicoDB1);
        }

        //Guardamos los productos en el carrito del usuario con su cantidad
        carritoService.saveProducto(productosDevueltos.get(0).getId(),user.getId(), 10);
        carritoService.saveProducto(productosDevueltos.get(1).getId(),user.getId(), 12);
        carritoService.saveProducto(productosDevueltos.get(2).getId(),user.getId(), 8);

        //Realizamos el pedido
        Pedido pedido = new Pedido();
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setUsuario(user);
        List<Carrito> carritodelusuario = carritoService.findCarritoByUsername(user.getUsername());
        List<item_pedido> listaitems = new ArrayList<>();

        List <Historico_precios> conjuntoprecios= new ArrayList<>();
        for (int i=0; i<carritodelusuario.size(); i++){
            Producto producto=carritodelusuario.get(i).getProductos();
            int cantidad = carritodelusuario.get(i).getCantidad();
            conjuntoprecios= historicoService.findByProductId(producto.getId());
            //Al estar trabajando directamente con los servicios es necesario que ponga esto aqui,
            //ya que sino, el al crear solamente el producto, el historico no se crea directamente,
            //para nuestra aplicacion eso lo hacemos en el controller.
            Historico_precios precio = conjuntoprecios.get(conjuntoprecios.size()-1);
            item_pedido item = new item_pedido();
            item.setCantidad(cantidad);
            item.setPrecio(precio);
            item.setProducto(producto);
            item_pedido itemPersistido=itemService.save(item);
            producto.setCantidad(producto.getCantidad()-cantidad);
            productoService.saveProduct(producto);
            listaitems.add(itemPersistido);
            for (i=0; i<carritodelusuario.size(); i++){
                carritoService.delete(carritodelusuario.get(i).getId());
            }
        }
        pedido.setItem_pedido(listaitems);
        pedido.setEstado(Pedido.Estado_envio.NOTIFICADO);
        Pedido pedidoentregado = pedidoService.save(pedido);
        LocalDateTime fecha1 = LocalDateTime.of(2015,
                Month.JULY, 29, 19, 30, 40);
        LocalDateTime fecha2 = LocalDateTime.of(2019,
                Month.JULY, 29, 19, 30, 40);
        List<Pedido> pedidodevuelto = pedidoService.findPedidoByUsernameEstadoyFechas(user.getUsername(),Pedido.Estado_envio.NOTIFICADO,fecha1, fecha2);
        assertEquals(0, pedidodevuelto.size());



        //Proceso de eliminacion de objetos necesarios
        pedidoService.delete(pedidoentregado.getId());
        for (int i = 0; i<listaitems.size(); i++){
            itemService.delete(listaitems.get(i).getId());
        }
        adminRepository.delete(admin);
        user_generalRepository.delete(user);
        for (int i=0; i<historicos.size(); i++){
            historicoService.delete(historicos.get(i).getId());
        }
        productoService.deleteProduct(productosDevueltos.get(0).getId());
        productoService.deleteProduct(productosDevueltos.get(1).getId());
        productoService.deleteProduct(productosDevueltos.get(2).getId());
        productoService.deleteProduct(productosDevueltos.get(3).getId());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testBuscaPedidoPorUsernameEstadoMalyFechas() {
        creaLineas();
        //Crea los usuarios (administrador y usuario)
        Admin admin = creaAdminNormal();
        adminRepository.save(admin);
        User_general user = creaUsuarioNormal();
        user_generalRepository.save(user);

        //Creamos los productos y guardamos sus precios en su respectivo historico de precios
        List<Producto> productosDevueltos = crearYGuardarProductos();
        List<Historico_precios> historicos = new ArrayList<>();
        for (int j = 0; j<productosDevueltos.size(); j++){
            Historico_precios historico1 = new Historico_precios(LocalDateTime.now(), productosDevueltos.get(0), productosDevueltos.get(0).getPrecio());
            Historico_precios historicoDB1 = historicoService.save(historico1);
            historicos.add(historicoDB1);
        }

        //Guardamos los productos en el carrito del usuario con su cantidad
        carritoService.saveProducto(productosDevueltos.get(0).getId(),user.getId(), 10);
        carritoService.saveProducto(productosDevueltos.get(1).getId(),user.getId(), 12);
        carritoService.saveProducto(productosDevueltos.get(2).getId(),user.getId(), 8);

        //Realizamos el pedido
        Pedido pedido = new Pedido();
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setUsuario(user);
        List<Carrito> carritodelusuario = carritoService.findCarritoByUsername(user.getUsername());
        List<item_pedido> listaitems = new ArrayList<>();

        List <Historico_precios> conjuntoprecios= new ArrayList<>();
        for (int i=0; i<carritodelusuario.size(); i++){
            Producto producto=carritodelusuario.get(i).getProductos();
            int cantidad = carritodelusuario.get(i).getCantidad();
            conjuntoprecios= historicoService.findByProductId(producto.getId());
            //Al estar trabajando directamente con los servicios es necesario que ponga esto aqui,
            //ya que sino, el al crear solamente el producto, el historico no se crea directamente,
            //para nuestra aplicacion eso lo hacemos en el controller.
            Historico_precios precio = conjuntoprecios.get(conjuntoprecios.size()-1);
            item_pedido item = new item_pedido();
            item.setCantidad(cantidad);
            item.setPrecio(precio);
            item.setProducto(producto);
            item_pedido itemPersistido=itemService.save(item);
            producto.setCantidad(producto.getCantidad()-cantidad);
            productoService.saveProduct(producto);
            listaitems.add(itemPersistido);
            for (i=0; i<carritodelusuario.size(); i++){
                carritoService.delete(carritodelusuario.get(i).getId());
            }
        }
        pedido.setItem_pedido(listaitems);
        pedido.setEstado(Pedido.Estado_envio.NOTIFICADO);
        Pedido pedidoentregado = pedidoService.save(pedido);
        LocalDateTime fecha1 = LocalDateTime.of(2015,
                Month.JULY, 29, 19, 30, 40);
        LocalDateTime fecha2 = LocalDateTime.of(2022,
                Month.JULY, 29, 19, 30, 40);
        List<Pedido> pedidodevuelto = pedidoService.findPedidoByUsernameEstadoyFechas(user.getUsername(),Pedido.Estado_envio.ENVIADO,fecha1, fecha2);
        assertEquals(0, pedidodevuelto.size());



        //Proceso de eliminacion de objetos necesarios
        pedidoService.delete(pedidoentregado.getId());
        for (int i = 0; i<listaitems.size(); i++){
            itemService.delete(listaitems.get(i).getId());
        }
        adminRepository.delete(admin);
        user_generalRepository.delete(user);
        for (int i=0; i<historicos.size(); i++){
            historicoService.delete(historicos.get(i).getId());
        }
        productoService.deleteProduct(productosDevueltos.get(0).getId());
        productoService.deleteProduct(productosDevueltos.get(1).getId());
        productoService.deleteProduct(productosDevueltos.get(2).getId());
        productoService.deleteProduct(productosDevueltos.get(3).getId());

    }




}
