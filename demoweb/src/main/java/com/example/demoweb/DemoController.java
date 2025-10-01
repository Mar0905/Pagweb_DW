package com.example.demoweb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DemoController {

    private List<Testimonio> testimonios = new ArrayList<>();

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("testimonios", testimonios);
        return "index";
    }

    @GetMapping("/productos")
    public String productos() {
        return "productos";
    }

    @GetMapping("/menu")
    public String menu() {
        return "menu";
    }

    @GetMapping("/novedades")
    public String novedades(Model model) {
        model.addAttribute("testimonios", testimonios);
        model.addAttribute("nuevoTestimonio", new Testimonio());
        return "novedades";
    }

    @PostMapping("/agregarTestimonio")
    public String agregarTestimonio(@ModelAttribute("nuevoTestimonio") Testimonio testimonio) {
        testimonios.add(testimonio);
        return "redirect:/novedades";
    }

    // ----------------- GET1 (Polo) -----------------
    @GetMapping("/get1")
    public String get1(
            @RequestParam("colorPolo") String colorPolo,
            @RequestParam("marcaPolo") String marcaPolo) {

        Testimonio t = new Testimonio();
        t.setColor(colorPolo);
        t.setMarca(marcaPolo);
        testimonios.add(t);

        return "redirect:/";
    }

    // ----------------- GET2 (Camisa) -----------------
    @GetMapping("/get2")
    public String get2(
            @RequestParam("colorCamisa") String colorCamisa,
            @RequestParam("marcaCamisa") String marcaCamisa) {

        Testimonio t = new Testimonio();
        t.setColor(colorCamisa);
        t.setMarca(marcaCamisa);
        testimonios.add(t);

        return "redirect:/";
    }

    // ----------------- POST1 (Pantalón) -----------------
    @PostMapping("/post1")
    public String post1(
            @RequestParam("colorPantalon") String colorPantalon,
            @RequestParam("marcaPantalon") String marcaPantalon) {

        Testimonio t = new Testimonio();
        t.setColor(colorPantalon);
        t.setMarca(marcaPantalon);
        testimonios.add(t);

        return "redirect:/";
    }

    // ----------------- POST2 (Zapato) -----------------
    @PostMapping("/post2")
    public String post2(
            @RequestParam("colorZapato") String colorZapato,
            @RequestParam("marcaZapato") String marcaZapato) {

        Testimonio t = new Testimonio();
        t.setColor(colorZapato);
        t.setMarca(marcaZapato);
        testimonios.add(t);

        return "redirect:/";
    }

    @GetMapping("/nosotros")
    public String nosotros() {
        return "nosotros";
    }

    @GetMapping("/contacto")
    public String contacto() {
        return "contacto";
    }
}
