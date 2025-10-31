package com.example.demoweb;

import com.example.demoweb.model.Ropa;
import com.example.demoweb.repository.RopaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DemoController {

    @Autowired
    private RopaRepository ropaRepo;

    private List<Testimonio2> testimoniosNovedades = new java.util.ArrayList<>();

    // ------------------- INDEX -------------------
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("testimonios", ropaRepo.findAll());
        return "index";
    }

    @GetMapping("/get1")
    public String get1(@RequestParam("colorPolo") String colorPolo,
                       @RequestParam("marcaPolo") String marcaPolo) {
        ropaRepo.save(new Ropa("GET1 - Polo", colorPolo, marcaPolo));
        return "redirect:/";
    }

    @GetMapping("/get2")
    public String get2(@RequestParam("colorCamisa") String colorCamisa,
                       @RequestParam("marcaCamisa") String marcaCamisa) {
        ropaRepo.save(new Ropa("GET2 - Camisa", colorCamisa, marcaCamisa));
        return "redirect:/";
    }

    @PostMapping("/post1")
    public String post1(@RequestParam("colorPantalon") String colorPantalon,
                        @RequestParam("marcaPantalon") String marcaPantalon) {
        ropaRepo.save(new Ropa("POST1 - Pantalón", colorPantalon, marcaPantalon));
        return "redirect:/";
    }

    @PostMapping("/post2")
    public String post2(@RequestParam("colorZapato") String colorZapato,
                        @RequestParam("marcaZapato") String marcaZapato) {
        ropaRepo.save(new Ropa("POST2 - Zapato", colorZapato, marcaZapato));
        return "redirect:/";
    }

    /* ------------------- NOVEDADES -------------------
    @GetMapping("/novedades")
    public String novedades(Model model) {
        model.addAttribute("testimoniosNovedades", testimoniosNovedades);
        model.addAttribute("nuevoTestimonio2", new Testimonio2());
        return "novedades";
    }

    @PostMapping("/agregarTestimonioNovedad")
    public String agregarTestimonioNovedad(@ModelAttribute("nuevoTestimonio2") Testimonio2 testimonio) {
        testimoniosNovedades.add(testimonio);
        return "redirect:/novedades";
    }
   */

    // ------------------- OTROS -------------------
    @GetMapping("/menu")
    public String menu() { return "menu"; }

    @GetMapping("/contacto")
    public String contacto() { return "contacto"; }
}

