package com.example.demoweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demoweb.model.Contacto;
import com.example.demoweb.repository.ContactoRepository;

@Controller
public class ContactoController {

    @Autowired
    private ContactoRepository contactoRepository;

    @PostMapping("/enviar")
    public String enviarFormulario(@ModelAttribute Contacto contacto) {
        contactoRepository.save(contacto);
        return "redirect:/contacto?exito";
    }
}