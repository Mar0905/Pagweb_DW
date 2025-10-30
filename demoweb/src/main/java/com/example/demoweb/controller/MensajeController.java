package com.example.demoweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demoweb.repository.ContactoRepository;

@Controller
public class MensajeController {

    @Autowired
    private ContactoRepository contactoRepository;

    @GetMapping("/mensajes")
    public String verMensajes(Model model) {
        model.addAttribute("mensajes", contactoRepository.findAll());
        return "mensajes"; // buscará el archivo mensajes.html en templates
    }
}
