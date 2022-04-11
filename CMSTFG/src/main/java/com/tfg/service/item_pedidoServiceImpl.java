package com.tfg.service;

import com.tfg.dao.Iitem_pedidoDao;
import com.tfg.entity.Pedido;
import com.tfg.entity.item_pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class item_pedidoServiceImpl implements Iitem_pedidoService{

    @Autowired
    private Iitem_pedidoDao itemDao;

    @Override
    public item_pedido findItemPedidoById(long id) {
        return itemDao.findItemPedidoById(id);
    }

    @Override
    public item_pedido save(item_pedido item_pedido) {
        return itemDao.save(item_pedido);
    }

    @Override
    public void delete(long id) {
        itemDao.deleteById(id);    }
}
