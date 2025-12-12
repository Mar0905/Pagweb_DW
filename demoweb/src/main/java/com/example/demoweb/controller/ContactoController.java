package com.example.demoweb.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demoweb.model.Contacto;
import com.example.demoweb.repository.ContactoRepository;
import com.example.demoweb.model.Usuario;

@Controller
public class ContactoController {

    @Autowired
    private ContactoRepository contactoRepository;

    // Mostrar formulario con los datos del usuario ya llenados
    @GetMapping("/contacto")
    public String mostrarFormularioContacto(Model model, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // Enviar datos al HTML
        model.addAttribute("usuario", usuario);

        // Objeto vacío para el formulario
        model.addAttribute("contacto", new Contacto());

        return "contacto";
    }

    // Guardar el mensaje
    @PostMapping("/enviar")
    public String enviarFormularioContacto(
            @ModelAttribute("contacto") Contacto contacto,
            HttpSession session,
            RedirectAttributes ra) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario != null) {
            // Autocompletar datos
            contacto.setNombre(usuario.getNombre() + " " + usuario.getApellido());
            contacto.setCorreo(usuario.getEmail());
        }

        contactoRepository.save(contacto);

        ra.addFlashAttribute("exito", "Mensaje enviado correctamente");
        return "redirect:/contacto";
    }
}
