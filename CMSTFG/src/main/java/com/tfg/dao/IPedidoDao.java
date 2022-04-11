package com.tfg.dao;

import com.tfg.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface IPedidoDao extends JpaRepository<Pedido, Long> {

    @Query(value= "select p from Pedido p where p.id = :id" )
    public Pedido findPedidoById(long id);


    @Query(value= "select p from Pedido p where p.usuario.username = :username" )
    public List<Pedido> findPedidoByUsername(String username);

    @Query(value= "select p from Pedido p where p.estado = :estadoent" )
    public List<Pedido> findPedidoByEstado(Pedido.Estado_envio estadoent);

    @Query(value= "select p from Pedido p where ((p.usuario.username = :username ) and (p.estado = :estado))")
    public List<Pedido> findPedidoByUsernameyEstado(String username, Pedido.Estado_envio estado);


    @Query(value= "select p from Pedido p where (" +
            "(p.fechaPedido BETWEEN :fechaInicio AND :fechaFin))" )
    public List<Pedido> findPedidoByFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);


    @Query(value= "select p from Pedido p where ((p.usuario.username = :username) and" +
            "(p.fechaPedido BETWEEN :fechaInicio AND :fechaFin))" )
    public List<Pedido> findPedidoByUsernameyFechas(String username, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    @Query(value= "select p from Pedido p where ((p.estado = :estadoent) and" +
            "(p.fechaPedido BETWEEN :fechaInicio AND :fechaFin))" )
    public List<Pedido> findPedidoByEstadoyFechas(Pedido.Estado_envio estadoent,
                                                  LocalDateTime fechaInicio, LocalDateTime fechaFin);

    @Query(value= "select p from Pedido p where ((p.usuario.username = :username ) and (p.estado = :estado) and" +
            "(p.fechaPedido BETWEEN :fechaInicio AND :fechaFin))")
    public List<Pedido> findPedidoByUsernameEstadoyFechas(String username, Pedido.Estado_envio estado,
                                                          LocalDateTime fechaInicio, LocalDateTime fechaFin);

}
