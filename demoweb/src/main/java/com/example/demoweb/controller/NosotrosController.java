package com.example.demoweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demoweb.model.Nosotros;
import com.example.demoweb.repository.NosotrosRepository;

@Controller
public class NosotrosController {

    @Autowired
    private NosotrosRepository nosotrosRepository;

    @GetMapping("/nosotros")
    public String mostrarNosotros(Model model) {
        // Cargamos el primer registro de la tabla "nosotros"
        Nosotros info = nosotrosRepository.findAll().stream().findFirst().orElse(null);
        model.addAttribute("info", info);
        return "nosotros";
    }

    // Endpoint para AJAX si se quieren cargar por separado
    @GetMapping("/api/nosotros")
    @ResponseBody
    public Nosotros obtenerNosotros() {
        return nosotrosRepository.findAll().stream().findFirst().orElse(null);
    }
}
