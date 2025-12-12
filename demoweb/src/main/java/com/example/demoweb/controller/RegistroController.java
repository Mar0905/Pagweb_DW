package com.example.demoweb.controller;


import com.example.demoweb.model.Usuario;
import com.example.demoweb.service.UsuarioService;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    // Mostrar el formulario de registro
    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("cliente", new Usuario());
        return "registro"; 
    }

    // Procesar el formulario de registro
    @PostMapping("/registro")
    public String procesarRegistro(
            @ModelAttribute Usuario usuarioForm,
            RedirectAttributes ra,
            HttpSession session
    ) {
        try {
            // Registrar al usuario (service verifica duplicados)
            Usuario nuevo = usuarioService.registrar(usuarioForm);

            // Iniciar sesión automáticamente
            session.setAttribute("cliente", nuevo);
            session.setAttribute("rol", nuevo.getRol());
            session.setAttribute("nombreAdmin", nuevo.getNombre());

            ra.addFlashAttribute("msg", "Cuenta creada exitosamente");

            return "redirect:/";
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
            return "redirect:/registro";
        }
    }
}
