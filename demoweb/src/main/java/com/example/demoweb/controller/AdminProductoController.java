package com.example.demoweb.controller;

import com.example.demoweb.model.Producto;
import com.example.demoweb.service.ProductoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/admin/productos")
public class AdminProductoController {

	@Autowired
	private ProductoService productoService;

	//  LISTAR
//	Muestra la lista completa de productos en el panel del admin.
	@GetMapping
	public String listar(Model model, HttpSession session) {
		if (!esAdmin(session))
			return "redirect:/login";
		model.addAttribute("productos", productoService.listarTodos());
		model.addAttribute("nombreAdmin", session.getAttribute("nombreAdmin"));
		return "admin-almacenamiento";
	}

	// CREAR NUEVO PRODUCTO
//	Muestra el formulario para crear un nuevo producto
	@GetMapping("/nuevo")
	public String nuevo(Model model, HttpSession session) {
		if (!esAdmin(session))
			return "redirect:/login";
		model.addAttribute("producto", new Producto());
		model.addAttribute("accion", "Crear");
		model.addAttribute("nombreAdmin", session.getAttribute("nombreAdmin"));
		return "editar-almacenamiento";
	}

	// EDITAR PRODUCTO
//	Muestra el formulario con los datos del producto para editar
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Long id, Model model, HttpSession session) {
		if (!esAdmin(session))
			return "redirect:/login";
		Optional<Producto> opt = productoService.porId(id);
		if (opt.isEmpty())
			return "redirect:/admin/productos";
		model.addAttribute("producto", opt.get());
		model.addAttribute("accion", "Actualizar");
		model.addAttribute("nombreAdmin", session.getAttribute("nombreAdmin"));
		return "editar-almacenamiento";
	}

	// GUARDAR PRODUCTO
//	Procesa el formulario de crear o actualizar producto.
	@PostMapping("/guardar")
	public String guardar(@ModelAttribute Producto productoForm, 
	                      RedirectAttributes ra, 
	                      HttpSession session) {
	    if (!esAdmin(session)) return "redirect:/login";

	    try {
	        if (productoForm.getIdProducto() != null && productoForm.getIdProducto() > 0) {
	     
	            Optional<Producto> opt = productoService.porId(productoForm.getIdProducto());
	            if (opt.isEmpty()) {
	                ra.addFlashAttribute("error", "Producto no encontrado");
	                return "redirect:/admin/productos";
	            }
	            Producto original = opt.get();
	            original.setNombre(productoForm.getNombre());
	            original.setDescripcion(productoForm.getDescripcion());
	            original.setPrecio(productoForm.getPrecio());
	            original.setStock(productoForm.getStock());
	            original.setTalla(productoForm.getTalla());
	            original.setColor(productoForm.getColor());
	            original.setMarca(productoForm.getMarca());
	            original.setImagenUrl(productoForm.getImagenUrl());
	            original.setActivo(productoForm.getActivo() != null ? productoForm.getActivo() : true);

	            productoService.guardar(original);
	            ra.addFlashAttribute("msg", "Producto actualizado exitosamente");
	        } else {
	            // CREACIÓN
	            productoForm.setFechaCreacion(LocalDateTime.now());
	            productoForm.setActivo(productoForm.getActivo() != null ? productoForm.getActivo() : true);
	            productoService.guardar(productoForm);
	            ra.addFlashAttribute("msg", "Producto creado exitosamente");
	        }
	        return "redirect:/admin/productos";
	    } catch (Exception e) {
	        ra.addFlashAttribute("error", "Error: " + e.getMessage());
	        return "redirect:/admin/productos";
	    }
	}

	// ELIMINAR PRODUCTO
//	Elimina un producto por su ID.
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Long id, RedirectAttributes ra, HttpSession session) {
		if (!esAdmin(session))
			return "redirect:/login";
		productoService.eliminar(id);
		ra.addFlashAttribute("msg", "Producto eliminado");
		return "redirect:/admin/productos";
	}

//	Verificar si tiene el rol adminstrador
	
	private boolean esAdmin(HttpSession session) {
		return "admin".equalsIgnoreCase((String) session.getAttribute("rol"));
	}
}