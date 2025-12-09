package com.example.demoweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demoweb.service.ProductoService;
import org.springframework.ui.Model;

//ProductoController.java
@Controller
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	@GetMapping("/productos")
	public String mostrarCatalogo(Model model) {
		model.addAttribute("productos", productoService.listarActivos());
		return "productos";
	}
		
}