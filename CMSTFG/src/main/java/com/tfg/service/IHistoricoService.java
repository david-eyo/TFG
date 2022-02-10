package com.tfg.service;

import com.tfg.entity.Historico_precios;

import java.util.List;

public interface IHistoricoService {

	public Historico_precios save(Historico_precios precio);
	
	public Historico_precios findById(long id);

	public List<Historico_precios> findByProductId(long id);

	public Historico_precios findCurrentPrice(long id);
	
	public void delete(long id);
}
