package com.tfg.service;

import com.tfg.entity.item_pedido;

public interface Iitem_pedidoService {

    public item_pedido findItemPedidoById(long id);

    public item_pedido save(item_pedido item_pedido);

    public void delete(long id);
}
