package com.tfg.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.entity.Producto;
import com.tfg.service.IProductoService;

@RestController
@RequestMapping (value = "/productos")
public class ProductoController {
	
	@Autowired
	private IProductoService productoService; 
	
	
	
	@GetMapping
	public ResponseEntity< List<Producto> > findAll(@RequestParam( required = false ) Integer page,
			@RequestParam( required = false ) Integer size){	
		
		Sort sortByName = Sort.by("nombre");
		List <Producto> productos = null;
		
		
		if (page != null && size != null) {
			
			Pageable pageable = PageRequest.of(page, size, sortByName);			
			productos = productoService.findAll(pageable).getContent();	
			
			
			
			if (productos.size() > 0) {
				
				return new ResponseEntity<List<Producto>>( productos, HttpStatus.OK );
			
			}else {
				
				return new ResponseEntity<List<Producto>>( HttpStatus.NO_CONTENT );	
			
			}
			
		}else {
			productos = productoService.findAll(sortByName);
			
			if (productos.size() > 0) {
				
				return new ResponseEntity<List<Producto>>( productos, HttpStatus.OK );
			
			}else {
				
				return new ResponseEntity<List<Producto>>( HttpStatus.NO_CONTENT );	
			
			}
			
		}

		
	}
	
	
	@GetMapping ( value = "/{id}" )
	public ResponseEntity< Producto > findById(@PathVariable int id){
		
		Producto producto = productoService.findById(id);
		ResponseEntity<Producto> responseEntity = null;
		
		if (producto != null) {
			responseEntity = new ResponseEntity<Producto>( producto, HttpStatus.OK);
		}else {
			responseEntity = new ResponseEntity<Producto>( producto, HttpStatus.NO_CONTENT);
		}
				
		return responseEntity;
	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> insert (@Valid @RequestBody Producto producto, BindingResult result){
		
		Map <String, Object> responseAsMap = new HashMap<String, Object>();
		ResponseEntity<Map<String, Object>> responseEntity = null;
		List<String> errores = null;
		
		if (result.hasErrors()) {
			errores = new ArrayList<String>();
			for (ObjectError error: result.getAllErrors()) {
				errores.add( error.getDefaultMessage() );
			}
			responseAsMap.put("errors", errores);
			responseEntity = new ResponseEntity<Map<String,Object>>( responseAsMap, HttpStatus.BAD_REQUEST);
			
		}
		
			
		Producto productoFromDB = productoService.saveProduct(producto);
			
		if ( productoFromDB != null ) {
			responseAsMap.put("producto", producto);
			responseAsMap.put("mensaje", "el producto con id "+ producto.getId() + "se ha creado con exito");
			responseEntity = new ResponseEntity<Map<String,Object>>(responseAsMap, HttpStatus.OK);
		}else {
			responseAsMap.put("mensaje", "el producto no se ha creado exitosamente");
			responseEntity = new ResponseEntity<Map<String,Object>>(responseAsMap, HttpStatus.BAD_REQUEST);				
		}
		return responseEntity;
	}
	
	
}
