package com.example.demoweb.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String verCarrito(Model model, HttpSession session, RedirectAttributes ra) {

        // Validación: si no está logueado → login
        if (session.getAttribute("usuario") == null) {
            ra.addFlashAttribute("error", "Debes iniciar sesión para ver tu carrito.");
            return "redirect:/login";
        }

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
    @ResponseBody
    public String agregar(@RequestParam("idProducto") Long idProducto,
                          HttpSession session) {

        // Validación de login
        if (session.getAttribute("usuario") == null) {
            return "LOGIN_REQUIRED";  // Respuesta fetch()
        }

        carritoService.agregarProducto(idProducto);
        return "OK"; 
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idProducto") Long idProducto,
                           HttpSession session,
                           RedirectAttributes ra) {

        if (session.getAttribute("usuario") == null) {
            ra.addFlashAttribute("error", "Debes iniciar sesión para manejar tu carrito.");
            return "redirect:/login";
        }

        carritoService.eliminarProducto(idProducto);
        return "redirect:/carrito";
    }

    @PostMapping("/aumentar")
    public String aumentar(@RequestParam("idProducto") Long idProducto,
                           HttpSession session,
                           RedirectAttributes ra) {

        if (session.getAttribute("usuario") == null) {
            ra.addFlashAttribute("error", "Debes iniciar sesión para manejar tu carrito.");
            return "redirect:/login";
        }

        carritoService.obtenerCarrito().actualizarCantidad(idProducto,
                carritoService.obtenerCarrito().getItems().stream()
                    .filter(i -> i.getProducto().getIdProducto().equals(idProducto))
                    .findFirst()
                    .map(i -> i.getCantidad() + 1)
                    .orElse(1));
        return "redirect:/carrito";
    }

    @PostMapping("/disminuir")
    public String disminuir(@RequestParam("idProducto") Long idProducto,
                            HttpSession session,
                            RedirectAttributes ra) {

        if (session.getAttribute("usuario") == null) {
            ra.addFlashAttribute("error", "Debes iniciar sesión para manejar tu carrito.");
            return "redirect:/login";
        }

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
    public String checkout(HttpSession session, RedirectAttributes ra) {

        if (session.getAttribute("usuario") == null) {
            ra.addFlashAttribute("error", "Debes iniciar sesión para completar la compra.");
            return "redirect:/login";
        }

        pedidoService.crearPedidoDesdeCarrito(carritoService.obtenerCarrito());
        carritoService.obtenerCarrito().getItems().clear();
        return "redirect:/pedido/confirmado"; 
    }
}

