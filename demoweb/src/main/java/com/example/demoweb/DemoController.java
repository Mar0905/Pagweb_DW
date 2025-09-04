package com.example.demoweb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DemoController {

    // lista donde se guarda temporalmente los testimonios
    private List<Testimonio> testimonios = new ArrayList<>();

    @GetMapping("/")
    public String index() {
        return "index"; // busca index.html en templates
    }

    @GetMapping("/productos")
    public String productos() {
        return "productos"; // busca productos.html en templates
    }

    @GetMapping("/menu")
    public String menu() {
        return "menu"; // busca menu.html en templates
    }

    @GetMapping("/novedades")
    public String novedades(Model model) {
        model.addAttribute("testimonios", testimonios); // lista de testimonios
        model.addAttribute("nuevoTestimonio", new Testimonio());
        return "novedades"; // busca novedades.html en templates
    }

    @PostMapping("/agregarTestimonio")
    public String agregarTestimonio(@ModelAttribute("nuevoTestimonio") Testimonio testimonio) {
        testimonios.add(testimonio);
        return "redirect:/novedades"; // redirige a la página de novedades
    }
}

