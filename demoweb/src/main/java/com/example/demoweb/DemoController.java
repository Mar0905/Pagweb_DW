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

    // ✅ Lista para index (GET/POST de ropa)
    private List<Testimonio> ropa = new ArrayList<>();

    private List<Testimonio2> testimoniosNovedades = new ArrayList<>();

    // ------------------- INDEX -------------------
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("testimonios", ropa);
        return "index";
    }

    @GetMapping("/get1")
    public String get1(@RequestParam("colorPolo") String colorPolo,
                       @RequestParam("marcaPolo") String marcaPolo) {
        ropa.add(new Testimonio(colorPolo, marcaPolo));
        return "redirect:/";
    }

    @GetMapping("/get2")
    public String get2(@RequestParam("colorCamisa") String colorCamisa,
                       @RequestParam("marcaCamisa") String marcaCamisa) {
        ropa.add(new Testimonio(colorCamisa, marcaCamisa));
        return "redirect:/";
    }

    @PostMapping("/post1")
    public String post1(@RequestParam("colorPantalon") String colorPantalon,
                        @RequestParam("marcaPantalon") String marcaPantalon) {
        ropa.add(new Testimonio(colorPantalon, marcaPantalon));
        return "redirect:/";
    }

    @PostMapping("/post2")
    public String post2(@RequestParam("colorZapato") String colorZapato,
                        @RequestParam("marcaZapato") String marcaZapato) {
        ropa.add(new Testimonio(colorZapato, marcaZapato));
        return "redirect:/";
    }

    // ------------------- NOVEDADES -------------------
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

    // ------------------- OTROS -------------------
    @GetMapping("/productos")
    public String productos() { return "productos"; }

    @GetMapping("/menu")
    public String menu() { return "menu"; }

    @GetMapping("/nosotros")
    public String nosotros() { return "nosotros"; }

    @GetMapping("/contacto")
    public String contacto() { return "contacto"; }
}
