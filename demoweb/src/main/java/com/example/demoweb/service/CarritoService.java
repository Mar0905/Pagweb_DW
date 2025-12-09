package com.example.demoweb.service;

import com.example.demoweb.model.Carrito;
import com.example.demoweb.model.Producto;
import com.example.demoweb.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class CarritoService {

    @Autowired
    private ProductoRepository productoRepository;

    private Carrito carrito = new Carrito();

    public void agregarProducto(Long idProducto) {
        Producto p = productoRepository.findById(idProducto).orElse(null);
        if (p != null) {
            carrito.agregar(p);
        }
    }

    public void eliminarProducto(Long idProducto) {
        carrito.eliminar(idProducto);
    }

    public Carrito obtenerCarrito() {
        return carrito;
    }
}



