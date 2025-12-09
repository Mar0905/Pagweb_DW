package com.example.demoweb.controller;

import java.util.Optional;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demoweb.model.Usuario;
import com.example.demoweb.service.UsuarioService;

@Controller
public class LoginController {
    @Autowired
    private UsuarioService usuarioService;

    // formulario de inicio de sesion
    @GetMapping({ "/inicio-sesion", "/login" })
    public String mostrarLogin() {
        return "inicio-sesion";
    }

    // formulario de login
    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam("email") String email,
            @RequestParam("password") String password, HttpSession session, RedirectAttributes ra) {

        Optional<Usuario> opt = usuarioService.autenticar(email, password);
        if (opt.isPresent()) {
            Usuario u = opt.get();
            session.setAttribute("usuario", u);
            session.setAttribute("rol", u.getRol());
            session.setAttribute("nombreAdmin", u.getNombre());

            if ("admin".equalsIgnoreCase(u.getRol())) {
                return "redirect:/admin/productos";
            } else {
                return "redirect:/";
            }
        }
        ra.addFlashAttribute("error", "Email o contraseña incorrectos");
        return "redirect:/login";
    }

    // Cierra la sesión actual y redirige al inicio
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
