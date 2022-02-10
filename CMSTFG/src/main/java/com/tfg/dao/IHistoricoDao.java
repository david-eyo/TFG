package com.tfg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfg.entity.Historico_precios;

import java.util.List;


public interface IHistoricoDao extends JpaRepository<Historico_precios, Long> {

	@Query (value= "select h from Historico_precios h where h.id = :id" )
	public Historico_precios findHistoricoById(long id);

	@Query (value= "select h from Historico_precios h where (h.fechaFin is null) and (h.producto.id = :idProducto)" )
	public Historico_precios findCurrentPrice(long idProducto);

	@Query (value= "select h from Historico_precios h where (h.producto.id=:id)" )
	public List<Historico_precios> findByProductId(long id);
}
