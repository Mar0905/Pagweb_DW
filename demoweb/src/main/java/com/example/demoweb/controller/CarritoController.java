package com.example.demoweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demoweb.service.CarritoService;
import com.example.demoweb.service.PedidoService;


@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public String verCarrito(Model model) {
        var carrito = carritoService.obtenerCarrito();

        model.addAttribute("items", carrito.getItems());
        model.addAttribute("subtotal", carrito.getSubtotal());
        model.addAttribute("igv", carrito.getIgv());
        model.addAttribute("total", carrito.getTotal());

        int totalCantidad = carrito.getItems().stream()
                                   .mapToInt(i -> i.getCantidad())
                                   .sum();
        model.addAttribute("totalCantidad", totalCantidad);

        return "carrito"; 
    }

    @PostMapping("/agregar")
    public String agregar(@RequestParam("idProducto") Long idProducto) {
        carritoService.agregarProducto(idProducto);
        return "redirect:/carrito";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idProducto") Long idProducto) {
        carritoService.eliminarProducto(idProducto);
        return "redirect:/carrito";
    }

    @PostMapping("/aumentar")
    public String aumentar(@RequestParam("idProducto") Long idProducto) {
        carritoService.obtenerCarrito().actualizarCantidad(idProducto,
                carritoService.obtenerCarrito().getItems().stream()
                    .filter(i -> i.getProducto().getIdProducto().equals(idProducto))
                    .findFirst()
                    .map(i -> i.getCantidad() + 1)
                    .orElse(1));
        return "redirect:/carrito";
    }

    @PostMapping("/disminuir")
    public String disminuir(@RequestParam("idProducto") Long idProducto) {
        carritoService.obtenerCarrito().actualizarCantidad(idProducto,
                carritoService.obtenerCarrito().getItems().stream()
                    .filter(i -> i.getProducto().getIdProducto().equals(idProducto))
                    .findFirst()
                    .map(i -> i.getCantidad() - 1)
                    .orElse(0));
        carritoService.obtenerCarrito().getItems().removeIf(i -> i.getCantidad() <= 0);
        return "redirect:/carrito";
    }

    @PostMapping("/checkout")
    public String checkout() {
        // Crear el pedido desde el carrito
        pedidoService.crearPedidoDesdeCarrito(carritoService.obtenerCarrito());

        // Limpiar carrito después del pedido
        carritoService.obtenerCarrito().getItems().clear();

        // Redirigir a página de confirmación
        return "redirect:/pedido/confirmado"; // ajusté la URL para que concuerde con PedidoController
    }
}

