package com.tfg.controller;

import com.tfg.entity.Historico_precios;
import com.tfg.entity.Producto;
import com.tfg.service.IHistoricoService;
import com.tfg.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/historico")
public class Historico_Controller {


    @Autowired
    private IHistoricoService historicoService;

    @Autowired
    private IProductoService productoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Historico_precios> findById(@PathVariable int id) {

        Historico_precios historico = historicoService.findById(id);
        ResponseEntity<Historico_precios> responseEntity = null;

        if (historico != null) {
            responseEntity = new ResponseEntity<Historico_precios>(historico, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<Historico_precios>(historico, HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<List<Historico_precios>> findByHistoricoProductId(@RequestParam(required = false) Long idProducto) {

        List<Historico_precios> historico = historicoService.findByProductId(idProducto);
        ResponseEntity<List<Historico_precios>> responseEntity = null;

        if (historico != null) {
            responseEntity = new ResponseEntity<List<Historico_precios>>(historico, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<List<Historico_precios>>(historico, HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> insert(
            @RequestParam (required = false) long idProducto,
                                                      @Valid @RequestBody Historico_precios historico,
                                                      BindingResult result) {

        Map<String, Object> responseAsMap = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> responseEntity = null;
        List<String> errores = null;

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
            
            Historico_precios historicoAnt = historicoService.findCurrentPrice(idProducto);

            historicoAnt.setFechaFin(LocalDateTime.now());

            Historico_precios historicoDevueltoAnt = historicoService.save(historicoAnt);

            Producto productoCorr = productoService.findById(idProducto);

            historico.setFechaIni(LocalDateTime.now());

            historico.setProducto(productoCorr);

            Historico_precios historicoDevuelto = historicoService.save(historico);

            if (historicoDevuelto != null && historicoAnt != null) {
                responseAsMap.put("historico", historico);
                responseAsMap.put("mensaje", "el historico con id " + historico.getId() + " se ha creado con exito");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.CREATED);
            } else {
                responseAsMap.put("mensaje", "el historico no se ha creado correctamente");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }

        } catch (DataAccessException e) {
            responseAsMap.put("mensaje",
                    "el historico no se ha creado correctamente: " + e.getMostSpecificCause().getMessage());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {

        Map<String, Object> responseAsMap = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> responseEntity = null;

        try {
            Historico_precios historico = historicoService.findById(id);
            if (historico == null) {
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.NOT_FOUND);
                responseAsMap.put("mensaje", "el historico con id " + id + " no existe");
            } else {
                productoService.deleteProduct(id);
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.NO_CONTENT);
            }

        } catch (DataAccessException e) {
            responseAsMap.put("mensaje",
                    "el historico no se ha podido eliminar correctamente: " + e.getMostSpecificCause().getMessage());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

}
