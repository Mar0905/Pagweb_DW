package com.example.demoweb.controller;

import com.example.demoweb.model.Testimonio;
import com.example.demoweb.repository.TestimonioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TestimonioController {

    @Autowired
    private TestimonioRepository testimonioRepository;

    // Mostrar página de novedades con testimonios
    @GetMapping("/novedades")
    public String mostrarNovedades(Model model) {
        List<Testimonio> testimonios = testimonioRepository.findAll();

        model.addAttribute("testimoniosNovedades", testimonios);
        model.addAttribute("nuevoTestimonio2", new Testimonio());

        return "novedades"; // nombre del HTML
    }

    // Se guarda nuevo testimonio desde el modal
    @PostMapping("/agregarTestimonioNovedad")
    public String guardarTestimonio(@ModelAttribute("nuevoTestimonio2") Testimonio testimonio) {
        testimonio.setVisible(true);
        testimonioRepository.save(testimonio);
        return "redirect:/novedades";
    }
}

