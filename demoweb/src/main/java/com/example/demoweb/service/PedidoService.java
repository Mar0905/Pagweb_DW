package com.example.demoweb.service;

import com.example.demoweb.model.Carrito;
import com.example.demoweb.model.Pedido;
import com.example.demoweb.model.PedidoItem;
import com.example.demoweb.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido crearPedidoDesdeCarrito(Carrito carrito) {
        Pedido pedido = new Pedido();
        pedido.setTotal(carrito.getTotal());

        carrito.getItems().forEach(ci -> {
            PedidoItem item = new PedidoItem();
            item.setProducto(ci.getProducto());
            item.setCantidad(ci.getCantidad());
            item.setSubtotal(ci.getSubtotal());
            pedido.agregarItem(item);
        });

        return pedidoRepository.save(pedido);
    }
}

