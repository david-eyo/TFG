package com.tfg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tfg.dao.IHistoricoDao;
import com.tfg.entity.Historico_precios;

import java.util.List;


@Service
public class HistoricoServiceImpl implements IHistoricoService {

    @Autowired
    private IHistoricoDao historicoDao;

    @Override
    @Transactional(readOnly = true)
    public Historico_precios findById(long id) {
        return historicoDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Historico_precios findCurrentPrice(long id){
        return historicoDao.findCurrentPrice(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Historico_precios> findByProductId(long id){
        return historicoDao.findByProductId(id);
    }

    @Override
    public Historico_precios save(Historico_precios precio) {
        return historicoDao.save(precio);
    }

}
