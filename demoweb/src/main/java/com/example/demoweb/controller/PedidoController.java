package com.example.demoweb.controller;

import com.example.demoweb.model.Carrito;
import com.example.demoweb.service.CarritoService;
import com.example.demoweb.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private PedidoService pedidoService;

    // POST para crear el pedido desde el carrito
    @PostMapping("/crear")
    public String crearPedido() {
        Carrito carrito = carritoService.obtenerCarrito();

        // Crear pedido en la BD
        pedidoService.crearPedidoDesdeCarrito(carrito);

        // Limpiar carrito
        carrito.getItems().clear();

        // Redirigir a página de confirmación
        return "redirect:/pedido/confirmado";
    }

    // GET para mostrar la confirmación
    @GetMapping("/confirmado")
    public String pedidoConfirmado() {
        return "pedido-confirmado"; // coincide con tu HTML
    }
}



