package com.tfg.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tfg.dao.IProductoDao;
import com.tfg.entity.*;
import com.tfg.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private IPedidoService pedidoService;

    @Autowired
    private ICarritoService carritoService;

    @Autowired
    private Iitem_pedidoService itemService;

    @Autowired
    private IHistoricoService historicoService;

    @Autowired
    private IProductoDao productoService;


    @GetMapping
    public ResponseEntity<List<Pedido>> findPedidoByUsername(@RequestParam(required = false) String username,
                                                             @RequestParam(required = false) String estadoent,
                           @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaIni,
                           @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaFin
    ) {

        ResponseEntity<List<Pedido>> responseEntity = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal().equals("anonymousUser")){
            responseEntity = new ResponseEntity<List<Pedido>>((List<Pedido>) null, HttpStatus.UNAUTHORIZED);
            return responseEntity;
        }
        CustomUserDetails currentPrincipalName = (CustomUserDetails) authentication.getPrincipal();
        User usuario = currentPrincipalName.getUser();

        List<Pedido> pedido=null;

        Pedido.Estado_envio estadoaux = null;


        if (estadoent != null){
            if (estadoent.equals("NOTIFICADO")){
                estadoaux= Pedido.Estado_envio.NOTIFICADO;
            }else if (estadoent.equals("ENVIADO")){
                estadoaux= Pedido.Estado_envio.ENVIADO;
            }else if (estadoent.equals("ENTREGA")){
                estadoaux= Pedido.Estado_envio.ENTREGA;
            }else if (estadoent.equals("ENTREGADO")){
                estadoaux= Pedido.Estado_envio.ENTREGADO;
            } else{
                responseEntity = new ResponseEntity<List<Pedido>>((List<Pedido>) null, HttpStatus.BAD_REQUEST);
                return responseEntity;
            }
        }

        LocalDateTime fechaIni2 = null;
        LocalDateTime fechaFin2 = null;
        if (fechaIni != null){
            fechaIni2 = fechaIni.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            if (fechaFin == null){
                fechaFin2 = LocalDateTime.now();
            }else {
                fechaFin2= fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            }
        }

        if (fechaIni == null){
            if ((username == null) && (estadoaux == null)){
                pedido=pedidoService.findPedidoByUsername(usuario.getUsername());
            }else if ((username == null) && (estadoaux != null)){
                if (usuario.getRol() != User.Rol.ROLE_ADMIN){
                    responseEntity = new ResponseEntity<List<Pedido>>((List<Pedido>) null, HttpStatus.UNAUTHORIZED);
                    return responseEntity;
                }else{
                    pedido=pedidoService.findPedidoByEstado(estadoaux);
                }
            }else if ((username != null) && (estadoaux == null)){
                if((!usuario.getUsername().equals(username) && (usuario.getRol() != User.Rol.ROLE_ADMIN))){
                    responseEntity = new ResponseEntity<List<Pedido>>((List<Pedido>) null, HttpStatus.UNAUTHORIZED);
                    return responseEntity;
                }else{
                    pedido=pedidoService.findPedidoByUsername(username);
                }
            }else if ((username != null) && (estadoaux != null)){
                if((!usuario.getUsername().equals(username) && (usuario.getRol() != User.Rol.ROLE_ADMIN))){
                    responseEntity = new ResponseEntity<List<Pedido>>((List<Pedido>) null, HttpStatus.UNAUTHORIZED);
                    return responseEntity;
                }else{
                    pedido=pedidoService.findPedidoByUsernameyEstado(username, estadoaux);
                }
            }
        }else{

            if ((username == null) && (estadoaux == null)){
                if (usuario.getRol() != User.Rol.ROLE_ADMIN){
                    responseEntity = new ResponseEntity<List<Pedido>>((List<Pedido>) null, HttpStatus.UNAUTHORIZED);
                    return responseEntity;
                }else{
                    pedido=pedidoService.findPedidoByFechas(fechaIni2, fechaFin2);
                }
            }else if ((username == null) && (estadoaux != null)){
                if (usuario.getRol() != User.Rol.ROLE_ADMIN){
                    responseEntity = new ResponseEntity<List<Pedido>>((List<Pedido>) null, HttpStatus.UNAUTHORIZED);
                    return responseEntity;
                }else{
                    pedido=pedidoService.findPedidoByEstadoyFechas(estadoaux, fechaIni2, fechaFin2);
                }
            }else if ((username != null) && (estadoaux == null)){
                if((!usuario.getUsername().equals(username) && (usuario.getRol() != User.Rol.ROLE_ADMIN))){
                    responseEntity = new ResponseEntity<List<Pedido>>((List<Pedido>) null, HttpStatus.UNAUTHORIZED);
                    return responseEntity;
                }else{
                    pedido=pedidoService.findPedidoByUsernameyFechas(username, fechaIni2, fechaFin2);
                }
            }else if ((username != null) && (estadoaux != null)){
                if((!usuario.getUsername().equals(username) && (usuario.getRol() != User.Rol.ROLE_ADMIN))){
                    responseEntity = new ResponseEntity<List<Pedido>>((List<Pedido>) null, HttpStatus.UNAUTHORIZED);
                    return responseEntity;
                }else{
                    pedido=pedidoService.findPedidoByUsernameEstadoyFechas(username, estadoaux, fechaIni2, fechaFin2);
                }
            }
        }

        if (pedido != null) {
            responseEntity = new ResponseEntity<List<Pedido>>(pedido, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<List<Pedido>>(pedido, HttpStatus.NO_CONTENT);
        }
        return responseEntity;
    }

    @PostMapping()
    public ResponseEntity<Map<String, Object>> addPedido() {

        Map<String, Object> responseAsMap = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> responseEntity = null;
        List<String> errores = null;


        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails currentPrincipalName = (CustomUserDetails) authentication.getPrincipal();
            User usuario = currentPrincipalName.getUser();

            User_general usergen = new User_general(usuario.getId(),
                    usuario.getUsername(), usuario.getPassword(), usuario.getNombre(), usuario.getApellidos(),
                    usuario.getEmail(), usuario.getCiudad(), usuario.getCp(),
                    usuario.getDireccion(), usuario.getFechaNacimiento(), usuario.getTlf());
            if (usuario.getRol() == User.Rol.ROLE_WORKER) {
                responseAsMap.put("mensaje",
                        "Los trabajadores no pueden realizar la compra");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.UNAUTHORIZED);
                return responseEntity;
            }
            List<Carrito> carritodelusuario = carritoService.findCarritoByUsername(usuario.getUsername());
            List<item_pedido> listaitems = new ArrayList<>();



            for (int i=0; i<carritodelusuario.size(); i++){
                Producto producto=carritodelusuario.get(i).getProductos();
                int cantidad = carritodelusuario.get(i).getCantidad();
                List <Historico_precios> conjuntoprecios= historicoService.findByProductId(producto.getId());
                Historico_precios precio = conjuntoprecios.get(conjuntoprecios.size()-1);
                item_pedido item = new item_pedido();
                item.setCantidad(cantidad);
                item.setPrecio(precio);
                item.setProducto(producto);
                item_pedido itemPersistido=itemService.save(item);
                producto.setCantidad(producto.getCantidad()-cantidad);
                productoService.save(producto);
                listaitems.add(itemPersistido);
                for (i=0; i<carritodelusuario.size(); i++){
                    carritoService.delete(carritodelusuario.get(i).getId());
                }

            }

            Pedido pedido = new Pedido();
            pedido.setUsuario(usuario);
            pedido.setItem_pedido(listaitems);
            pedido.setFechaPedido(LocalDateTime.now());
            pedido.setEstado(Pedido.Estado_envio.NOTIFICADO);

            Pedido pedidoentregado = pedidoService.save(pedido);

            responseAsMap.put("pedido", pedidoentregado);
            responseAsMap.put("mensaje", "El pedido se ha realizado correctamente.");
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.CREATED);
        } catch (Exception e) {
            responseAsMap.put("mensaje",
                    "Ha habido un problema realizando el pedido." + e.getMessage());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> updatePedido(@PathVariable long id
                                                                     ) {

        Map<String, Object> responseAsMap = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> responseEntity = null;
        List<String> errores = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails currentPrincipalName = (CustomUserDetails) authentication.getPrincipal();
        User usuario =currentPrincipalName.getUser();


/*        if (result.hasErrors()) {
            errores = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errores.add(error.getDefaultMessage());
            }
            responseAsMap.put("errors", errores);
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }*/

        try {
            Pedido pedido = pedidoService.findPedidoById(id);
            if (pedido == null){
                responseAsMap.put("mensaje",
                        "Pedido no existente" );
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.NOT_FOUND);
            }
            if (usuario.getRol() != User.Rol.ROLE_ADMIN){
                responseAsMap.put("mensaje",
                        "Sólo el administrador puede modificar el estado de un pedido." );
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.UNAUTHORIZED);
            }
            if (pedido.getEstado()== Pedido.Estado_envio.NOTIFICADO){
                pedido.setEstado(Pedido.Estado_envio.ENVIADO);
            }else if(pedido.getEstado()== Pedido.Estado_envio.ENVIADO){
                pedido.setEstado(Pedido.Estado_envio.ENTREGA);
            }else if(pedido.getEstado()== Pedido.Estado_envio.ENTREGA){
                pedido.setEstado(Pedido.Estado_envio.ENTREGADO);
            }else{
                responseAsMap.put("mensaje",
                        "Este pedido no puede cambiar de estado, ya ha sido entregado a su destinatario." );
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }

            Pedido pedidoFromDB = pedidoService.save(pedido);

            if (pedidoFromDB != null) {
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.NO_CONTENT);
            } else {
                responseAsMap.put("mensaje", "El pedido no se ha actualizado correctamente.");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }

        } catch (DataAccessException | ConstraintViolationException e) {
            responseAsMap.put("mensaje",
                    "El pedido no se ha actualizado correctamente." + e.getMessage());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

}
