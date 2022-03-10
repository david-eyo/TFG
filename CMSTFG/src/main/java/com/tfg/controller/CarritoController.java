package com.tfg.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;

import com.tfg.entity.*;
import com.tfg.service.CustomUserDetails;
import com.tfg.service.ICarritoService;
import com.tfg.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/carrito")
public class CarritoController {

    @Autowired
    private ICarritoService carritoService;
    @Autowired
    private IProductoService productoService;


    @PostMapping()
    public ResponseEntity<Map<String, Object>> addProductoToCarrito(@RequestBody ObjectNode objectNode){

        Map<String, Object> responseAsMap = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> responseEntity = null;
        List<String> errores = null;

        long productId = objectNode.get("productId").asLong();
        int cantidad = objectNode.get("cantidad").asInt();


        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails currentPrincipalName = (CustomUserDetails) authentication.getPrincipal();
            User usuario =currentPrincipalName.getUser();

            User_general usergen = new User_general(usuario.getId(),
                    usuario.getUsername(), usuario.getPassword(), usuario.getNombre(), usuario.getApellidos(),
                    usuario.getEmail(), usuario.getCiudad(), usuario.getCp(),
                    usuario.getDireccion(), usuario.getFechaNacimiento(), usuario.getTlf());
            if (usuario.getRol() == User.Rol.ROLE_WORKER){
                responseAsMap.put("mensaje",
                        "Los trabajadores no pueden tener un carrito de la compra");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.UNAUTHORIZED);
                return responseEntity;
            }
            Producto producto = productoService.findById(productId);
            if (producto == null){
                responseAsMap.put("mensaje",
                        "El producto no existe");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.NOT_FOUND);
                return responseEntity;
            }
            if (cantidad > producto.getCantidad()){
                responseAsMap.put("mensaje",
                        "La cantidad requerida excede a la cantidad existente del producto ");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
                return responseEntity;
            }
            Boolean ya = carritoService.productoYaEnCarrito(productId, usuario.getId());
            if(ya == true){
                responseAsMap.put("mensaje",
                        "El producto ya ha sido añadido al carrito con anterioridad ");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            Carrito carritoNuevo= new Carrito();
            carritoNuevo.setUsuario(usergen);
            carritoNuevo.setProductos(producto);
            carritoNuevo.setCantidad(cantidad);
            carritoService.save(carritoNuevo);

            responseAsMap.put("carrito", carritoNuevo);
            responseAsMap.put("mensaje", "el producto " + carritoNuevo.getProductos().getNombre() + " con cantidad " +
                    carritoNuevo.getCantidad() +" se ha añadido con exito al carrito del usuario "+carritoNuevo.getUsuario().getUsername());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.CREATED);
        }catch (Exception e) {
            responseAsMap.put("mensaje",
                    "el producto no se ha añadido correctamente al carrito: " + e.getMessage());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {

        Map<String, Object> responseAsMap = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> responseEntity = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails currentPrincipalName = (CustomUserDetails) authentication.getPrincipal();
        User usuario =currentPrincipalName.getUser();

        try {
            Carrito carrito = carritoService.findCarritoById(id);

            if (carrito == null) {
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.NOT_FOUND);
                responseAsMap.put("mensaje", "el carrito con id " + id + " no existe");
            } else {
                if((!usuario.getUsername().equals(carrito.getUsuario().getUsername())) && (usuario.getRol() != User.Rol.ROLE_ADMIN)){
                    responseAsMap.put("mensaje",
                            "No puede eliminar un elemento del carrito de la compra que no sea suyo.");
                    responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.UNAUTHORIZED);
                    return responseEntity;
                }
                carritoService.delete(id);
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.NO_CONTENT);
            }

        } catch (DataAccessException e) {
            responseAsMap.put("mensaje",
                    "el carrito no se ha podido eliminar correctamente: " + e.getMostSpecificCause().getMessage());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<List<Carrito>> findCarritoByUsername(@RequestParam(required = false) String username
                                                                ) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails currentPrincipalName = (CustomUserDetails) authentication.getPrincipal();
        User usuario =currentPrincipalName.getUser();

        List<Carrito> carrito = carritoService.findCarritoByUsername(username);

        ResponseEntity<List<Carrito>> responseEntity = null;


        if((!usuario.getUsername().equals(username) && (usuario.getRol() != User.Rol.ROLE_ADMIN))){
            responseEntity = new ResponseEntity<List<Carrito>>((List<Carrito>) null, HttpStatus.UNAUTHORIZED);
            return responseEntity;
        }


        if (carrito != null) {

            responseEntity = new ResponseEntity<List<Carrito>>(carrito, HttpStatus.OK);


        } else {
            responseEntity = new ResponseEntity<List<Carrito>>(carrito, HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> updateElementoCarrito(@PathVariable long id, @Valid @RequestBody ObjectNode objectNode,
                                                      BindingResult result) {

        Map<String, Object> responseAsMap = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> responseEntity = null;
        List<String> errores = null;

        long productId = objectNode.get("productId").asLong();
        int cantidad = objectNode.get("cantidad").asInt();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails currentPrincipalName = (CustomUserDetails) authentication.getPrincipal();
        User usuario =currentPrincipalName.getUser();


        if (result.hasErrors()) {
            errores = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errores.add(error.getDefaultMessage());
            }
            responseAsMap.put("errors", errores);
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        try {
            Producto producto = productoService.findById(productId);
            if (producto == null){
                responseAsMap.put("mensaje",
                        "Producto no existente" );
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.NOT_FOUND);
            }
            Carrito carrito = carritoService.findCarritoById(id);
            if (carrito == null){
                responseAsMap.put("mensaje",
                        "Elemento carrito inexistente" );
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.NOT_FOUND);
            }
            if ((usuario.getId() != carrito.getUsuario().getId())&&(usuario.getRol() != User.Rol.ROLE_ADMIN)){
                responseAsMap.put("mensaje",
                        "Sólo el propio usuario puede modificar el carrito propio." );
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.UNAUTHORIZED);
            }
            if (carrito.getProductos().getId() != productId){
                responseAsMap.put("mensaje",
                        "el producto no se ha actualizado correctamente: El id del producto que se desea cambiar (body)" +
                                "no coincide con el id del producto correspondiente al id del elemento carrito " +
                                "proporcionado en el path." );
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if (cantidad>producto.getCantidad()){
                responseAsMap.put("mensaje",
                        "Cantidad excesiva. No hay suficiente cantidad de este producto para poder actualizar este" +
                                " elemento del carrito." );
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            carrito.setCantidad(cantidad);
            Carrito carritoFromDB = carritoService.save(carrito);

            if (carritoFromDB != null) {
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.NO_CONTENT);
            } else {
                responseAsMap.put("mensaje", "el carrito no se ha actualizado correctamente");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }

        } catch (DataAccessException | ConstraintViolationException e) {
            responseAsMap.put("mensaje",
                    "el producto no se ha actualizado correctamente: " + e.getMessage());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }


}
