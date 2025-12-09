package com.example.demoweb;

import com.example.demoweb.model.ProductoEntity;
import com.example.demoweb.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DemoController {

    @Autowired
    private ProductoRepository productoRepo;


    // ------------------- INDEX -------------------
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("productos", productoRepo.findAll());
        return "index";
    }


    // 🔥 BUSQUEDA UNICA
    @GetMapping("/buscar-producto")
    public String buscarProducto(@RequestParam("color") String color,
                                 @RequestParam("marca") String marca,
                                 Model model) {

        List<ProductoEntity> encontrados =
                productoRepo.findByColorIgnoreCaseAndMarcaIgnoreCase(color, marca);

        model.addAttribute("productos", encontrados);
        return "index";
    }


    // ------------------- OTROS -------------------
    @GetMapping("/productos")
    public String productos() { return "productos"; }

    @GetMapping("/menu")
    public String menu() { return "menu"; }

    @GetMapping("/contacto")
    public String contacto() { return "contacto"; }

}

