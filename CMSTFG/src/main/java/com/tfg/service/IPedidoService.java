package com.tfg.service;

import com.tfg.entity.Pedido;

import java.time.LocalDateTime;
import java.util.List;

public interface IPedidoService {

    public Pedido findPedidoById(long id);

    public List<Pedido> findPedidoByUsername(String username);

    public List<Pedido> findPedidoByEstado(Pedido.Estado_envio estadoent);

    public List<Pedido> findPedidoByUsernameyEstado(String username, Pedido.Estado_envio estadoent);

    public List<Pedido> findPedidoByFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    public List<Pedido> findPedidoByUsernameyFechas(String username, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    public List<Pedido> findPedidoByEstadoyFechas(Pedido.Estado_envio estadoent,
                                                  LocalDateTime fechaInicio, LocalDateTime fechaFin);

    public List<Pedido> findPedidoByUsernameEstadoyFechas(String username, Pedido.Estado_envio estado,
                                                          LocalDateTime fechaInicio, LocalDateTime fechaFin);

    public Pedido save(Pedido pedido);

    public void delete(long id);
}
