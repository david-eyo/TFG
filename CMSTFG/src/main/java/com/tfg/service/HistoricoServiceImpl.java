package com.tfg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.tfg.dao.IHistoricoDao;
import com.tfg.entity.Historico_precios;


public class HistoricoServiceImpl implements IHistoricoService {
	
    @Autowired
	private IHistoricoDao historicoDao;
	
	@Override
	@Transactional( readOnly = true )
	public Historico_precios findById(long id) {
	    return historicoDao.findById(id).orElse(null);
	}

	@Override
	public Historico_precios savePrecio(Historico_precios precio) {
	    return historicoDao.save(precio);
	}

}
