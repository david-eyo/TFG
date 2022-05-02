package com.tfg.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.tfg.entity.UploadedFile;
import com.tfg.entity.User;
import com.tfg.response.FileUploadResponse;
import com.tfg.service.CustomUserDetails;
import com.tfg.service.IFileUploadService;
import com.tfg.service.IHistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.entity.Historico_precios;
import com.tfg.entity.Producto;
import com.tfg.service.IProductoService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IHistoricoService historicoService;

    @Autowired
    private IFileUploadService fileUploadService;

    @GetMapping
    public ResponseEntity<List<Producto>> findAll(@RequestParam(required = false) Integer page,
                                                  @RequestParam(required = false) Integer size, @RequestParam(required = false) String nombre,
                                                  @RequestParam(required = false) Boolean oferta,
                                                  @RequestParam(required = false) Boolean nuestros_productos) {

        Sort sortByName = Sort.by("nombre");
        List<Producto> productos = null;

        if (nombre == null && oferta != null && nuestros_productos == null) {
            productos = productoService.findByOferta();
            return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
        } else if (nombre == null && oferta == null && nuestros_productos != null) {
            productos = productoService.findByNuestrosProductos();
            return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);

        } else if (nombre == null && oferta != null && nuestros_productos != null) {
            productos = productoService.findByNuestrosProductos();
            for (Producto productoiter : productos) {
                if (!productoiter.isOferta()) {
                    productos.remove(productoiter);
                }
            }
            return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);

        } else if (nombre != null && page == null && size == null && oferta != null && nuestros_productos == null) {
            productos = productoService.findByName(nombre);
            for (Producto productoiter : productos) {
                if (!productoiter.isOferta()) {
                    productos.remove(productoiter);
                }
            }
            return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);

        } else if (nombre != null && page == null && size == null && oferta == null && nuestros_productos != null) {
            productos = productoService.findByName(nombre);
            for (Producto productoiter : productos) {
                if (!productoiter.isNuestros_productos()) {
                    productos.remove(productoiter);
                }
            }
            return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);

        } else if (nombre != null && page == null && size == null && oferta != null && nuestros_productos != null) {
            productos = productoService.findByName(nombre);
            for (Producto productoiter : productos) {
                if (!productoiter.isNuestros_productos() || !productoiter.isOferta()) {
                    productos.remove(productoiter);
                }
            }
            return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);

        } else if (nombre != null && page == null && size == null && oferta == null && nuestros_productos == null) {
            productos = productoService.findByName(nombre);
            return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);

        } else if (page != null && size != null) {
            Pageable pageable = PageRequest.of(page, size, sortByName);
            productos = productoService.findAll(pageable).getContent();
            if (productos.size() > 0) {
                return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
            } else {
                return new ResponseEntity<List<Producto>>(HttpStatus.NO_CONTENT);
            }
        } else {
            productos = productoService.findAll(sortByName);
            if (productos.size() > 0) {
                return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
            } else {
                return new ResponseEntity<List<Producto>>(HttpStatus.NO_CONTENT);
            }
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Producto> findById(@PathVariable int id) {

        Producto producto = productoService.findById(id);
        ResponseEntity<Producto> responseEntity = null;

        if (producto != null) {
            responseEntity = new ResponseEntity<Producto>(producto, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<Producto>(producto, HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }


    @PostMapping
    public ResponseEntity<Map<String, Object>> insert(@Valid @RequestBody Producto producto, BindingResult result) {

        Map<String, Object> responseAsMap = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> responseEntity = null;
        List<String> errores = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails currentPrincipalName = (CustomUserDetails) authentication.getPrincipal();
        User usuario =currentPrincipalName.getUser();

        if ((usuario.getRol() != User.Rol.ROLE_ADMIN)){
            responseAsMap.put("mensaje",
                    "Sólo el administrador puede crear un producto" );
            return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.UNAUTHORIZED);
        }

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

            Producto productoFromDB = productoService.saveProduct(producto);
            Historico_precios historico = new Historico_precios(LocalDateTime.now(), producto, producto.getPrecio());
            Historico_precios historicoDB = historicoService.save(historico);

            if (productoFromDB != null) {
                responseAsMap.put("producto", producto);
                responseAsMap.put("mensaje", "el producto con id " + producto.getId() + " se ha creado con exito");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.CREATED);
            } else {
                responseAsMap.put("mensaje", "el producto no se ha creado correctamente");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }

        } catch (DataAccessException e) {
            responseAsMap.put("mensaje",
                    "el producto no se ha creado correctamente: " + e.getMostSpecificCause().getMessage());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable long id, @Valid @RequestBody Producto producto,
                                                      BindingResult result) {

        Map<String, Object> responseAsMap = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> responseEntity = null;
        List<String> errores = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails currentPrincipalName = (CustomUserDetails) authentication.getPrincipal();
        User usuario =currentPrincipalName.getUser();

        if ((usuario.getRol() != User.Rol.ROLE_ADMIN)){
            responseAsMap.put("mensaje",
                    "Sólo el administrador puede actualizar un producto" );
            return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.UNAUTHORIZED);
        }

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

            if (id != producto.getId()) {
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
                responseAsMap.put("mensaje", " El id de la URI (" + id + ") :"
                        + "no corresponde con el id proporcionado en el body (" + producto.getId() + ").");
            } else {
                Producto productoBusca = productoService.findById(id);
                if (productoBusca == null) {
                    responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.NOT_FOUND);
                    responseAsMap.put("mensaje", " Producto con id: " + id + " no encontrado");
                } else {
					if(producto.getValoracion() != 0.0f){
						productoService.rateProduct((int) producto.getValoracion(),id);
                        return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.NO_CONTENT);
					}
					if(producto.getNumero_valoraciones() != 0.0f){
                        responseAsMap.put("mensaje", " Producto con id: " + id + " , se ha intentado cambiar el" +
                                "numero de valoraciones de forma no valida(no incluir en el cuerpo de la petición)");
						return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
					}
                    //Si cambia el precio, tenemos que crear una nueva entrada en nuestra tabla del histórico de precios.
                    //Pensé en que lanzara una excepción pero llegué a la conclusión de que es una tontería. Habrá
                    //2 formas de realizarlo diferentes. Una en el controlados del historico donde le indicaremos solo
                    //el id del producto del cual queremos cambiar el precio con el id correspondiente dle producto, y
                    //otro éste donde si cambian más cosas podamso también cambiar el historico.
                    if ((productoBusca.getPrecio() != producto.getPrecio())&&(producto.getPrecio()!=0f)) {
                        Historico_precios historicoAnt = historicoService.findCurrentPrice(producto.getId());
                        historicoAnt.setFechaFin(LocalDateTime.now());
                        Historico_precios historicoDevueltoAnt = historicoService.save(historicoAnt);

                        Historico_precios historico = new Historico_precios();
                        historico.setPrecio(producto.getPrecio());
                        historico.setProducto(producto);
                        historico.setFechaIni(LocalDateTime.now());
                        Historico_precios historicoNuevo = historicoService.save(historico);

                        Producto productoNuevo= productoService.findById(producto.getId());
                        productoNuevo.setPrecio(producto.getPrecio());
                        productoService.saveProduct(productoNuevo);

                        if (historicoDevueltoAnt != null) {
                            return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.NO_CONTENT);
                        } else {
                            responseAsMap.put("mensaje", "el historico no se ha actualizado correctamente al actualizar todo el producto");
                            return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
                        }
                    }
					Producto productoActualizado = new Producto();
					productoActualizado.setNombre(producto.getNombre());
					productoActualizado.setNuestros_productos(producto.isNuestros_productos());
					productoActualizado.setOferta(producto.isOferta());
					productoActualizado.setId(producto.getId());
					productoActualizado.setImage(producto.getImage());
					productoActualizado.setCantidad(producto.getCantidad());
                    Producto productoBusca2 = productoService.findById(producto.getId());
                    productoActualizado.setPrecio(productoBusca2.getPrecio());
                    productoActualizado.setValoracion(productoBusca2.getValoracion());
                    productoActualizado.setNumero_valoraciones(productoBusca2.getNumero_valoraciones());
                    Producto productoFromDB = productoService.saveProduct(productoActualizado);

                    if (productoFromDB != null) {
                        return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.NO_CONTENT);
                    } else {
                        responseAsMap.put("mensaje", "el producto no se ha actualizado correctamente");
                        return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
                    }
                }
            }

        } catch (DataAccessException |ConstraintViolationException e) {
            responseAsMap.put("mensaje",
                    "el producto no se ha actualizado correctamente: " + e.getMessage());
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

        if ((usuario.getRol() != User.Rol.ROLE_ADMIN)){
            responseAsMap.put("mensaje",
                    "Sólo el administrador puede eliminar un producto" );
            return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.UNAUTHORIZED);
        }

        try {
            Producto producto = productoService.findById(id);
            if (producto == null) {
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.NOT_FOUND);
                responseAsMap.put("mensaje", "el producto con id " + id + " no existe");
            } else {
                productoService.deleteProduct(id);
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.NO_CONTENT);
            }

        } catch (DataAccessException e) {
            responseAsMap.put("mensaje",
                    "el producto no se ha podido eliminar correctamente: " + e.getMostSpecificCause().getMessage());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    @PostMapping(value= "/prueba")
    public ResponseEntity<?> prueba(@RequestParam(required=false) MultipartFile file,@RequestParam String productostr) throws JsonProcessingException {

        Map<String, Object> responseAsMap = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> responseEntity = null;
        List<String> errores = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails currentPrincipalName = (CustomUserDetails) authentication.getPrincipal();
        User usuario =currentPrincipalName.getUser();

        if ((usuario.getRol() != User.Rol.ROLE_ADMIN)){
            responseAsMap.put("mensaje",
                    "Sólo el administrador puede crear un producto" );
            return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.UNAUTHORIZED);
        }

        try {
            Producto producto = new ObjectMapper().readValue(productostr, Producto.class);

            //Inserción de imágen del producto

            if (file != null){
                UploadedFile uploadedFile= fileUploadService.uploadToDb(file);
                String downloadUri= "";
                downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/imagenes/download/")
                        .path(uploadedFile.getFileId())
                        .toUriString();
                producto.setImage(downloadUri);
            }


            Producto productoFromDB = productoService.saveProduct(producto);
            Historico_precios historico = new Historico_precios(LocalDateTime.now(), producto, producto.getPrecio());
            historicoService.save(historico);

            if (productoFromDB != null) {
                responseAsMap.put("producto", producto);
                responseAsMap.put("mensaje", "el producto con id " + producto.getId() + " se ha creado con exito");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.CREATED);
            } else {
                responseAsMap.put("mensaje", "el producto no se ha creado correctamente");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
        } catch (DataAccessException e) {
            responseAsMap.put("mensaje",
                    "el producto no se ha creado correctamente: " + e.getMostSpecificCause().getMessage());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

}
