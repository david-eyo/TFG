package com.tfg.dao;

import com.tfg.entity.Pedido;
import com.tfg.entity.item_pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Iitem_pedidoDao extends JpaRepository<item_pedido, Long> {

    @Query(value= "select p from item_pedido p where p.id = :id" )
    public item_pedido findItemPedidoById(long id);
}
