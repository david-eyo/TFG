package com.tfg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfg.entity.Historico_precios;


public interface IHistoricoDao extends JpaRepository<Historico_precios, Long> {

	@Query (value= "select h from Historico_precios h where h.id = :id" )
	public Historico_precios findHistoricoById(long id);	
}
