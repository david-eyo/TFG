package com.tfg.service;


import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tfg.dao.IProductoDao;
import com.tfg.entity.Producto;



@Service
public class ProductoServiceImpl implements IProductoService {
	
	@Autowired
	private IProductoDao productoDao;

	@Override
	@Transactional( readOnly = true )
	public List<Producto> findAll(Sort sort) {
		return productoDao.findAll(sort);
	}

	@Override
	@Transactional( readOnly = true )
	public Page<Producto> findAll(Pageable pageable) {
		return productoDao.findAll(pageable);
	}

	@Override
	//@Transactional( readOnly = true )
	public Producto findById(long id) {
		return productoDao.findById(id);
	}

	@Override
	@Transactional( readOnly = true )
	public List<Producto> findByName(String nombre) {
		return productoDao.findByName(nombre);
	}

	@Override
	@Transactional( readOnly = true )
	public List<Producto> findByOferta() {
		return productoDao.findByOferta();
	}

	@Override
	@Transactional( readOnly = true )
	public List<Producto> findByNuestrosProductos() {
		return productoDao.findByNuestrosProductos();
	}

	@Override
	@Transactional
	public void deleteProduct(long id) {
		productoDao.deleteById(id);
	}

	@Override
	@Transactional
	public Producto saveProduct(Producto producto) {
		return productoDao.save(producto);
	}
	
	@Override
	@Transactional
	public void rateProduct(int nota, long idProducto) {
		if ((nota >=0) &&(nota <=5)) {
			productoDao.rateProducto(nota, idProducto);
		}else {
			throw new ConstraintViolationException("La nota debe estar entre 0 y 5", null);
		}
		 
	}
	

}
