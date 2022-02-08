package com.tfg.service;

import com.tfg.entity.Historico_precios;

public interface IHistoricoService {

	public Historico_precios savePrecio(Historico_precios precio);
	
	public Historico_precios findById(long id);
}
