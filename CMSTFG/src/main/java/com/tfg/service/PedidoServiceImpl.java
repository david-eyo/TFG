package com.tfg.service;

import com.tfg.dao.IPedidoDao;
import com.tfg.entity.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoServiceImpl implements IPedidoService{

    @Autowired
    private IPedidoDao pedidoDao;

    @Override
    public Pedido findPedidoById(long id) {
        return pedidoDao.findPedidoById(id);
    }

    @Override
    public List<Pedido> findPedidoByUsername(String username) {
        return pedidoDao.findPedidoByUsername(username);
    }

    public List<Pedido> findPedidoByEstado(Pedido.Estado_envio estadoent){
        return pedidoDao.findPedidoByEstado(estadoent);
    };

    public List<Pedido> findPedidoByUsernameyEstado(String username, Pedido.Estado_envio estadoent){
        return pedidoDao.findPedidoByUsernameyEstado(username, estadoent);
    };

    public List<Pedido> findPedidoByFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin){
        return pedidoDao.findPedidoByFechas(fechaInicio, fechaFin);
    };

    public List<Pedido> findPedidoByUsernameyFechas(String username, LocalDateTime fechaInicio, LocalDateTime fechaFin){
        return pedidoDao.findPedidoByUsernameyFechas(username, fechaInicio, fechaFin);
    };

    public List<Pedido> findPedidoByEstadoyFechas(Pedido.Estado_envio estadoent,
                                                  LocalDateTime fechaInicio, LocalDateTime fechaFin){
        return pedidoDao.findPedidoByEstadoyFechas(estadoent, fechaInicio, fechaFin);
    };

    public List<Pedido> findPedidoByUsernameEstadoyFechas(String username, Pedido.Estado_envio estado,
                                                          LocalDateTime fechaInicio, LocalDateTime fechaFin){
        return pedidoDao.findPedidoByUsernameEstadoyFechas(username, estado, fechaInicio, fechaFin);
    };

    @Override
    public Pedido save(Pedido pedido) {
        return pedidoDao.save(pedido);
    }

    @Override
    public void delete(long id) {
        pedidoDao.deleteById(id);
    }
}
