package com.example.demoweb;

import com.example.demoweb.model.ProductoEntity;
import com.example.demoweb.repository.ProductoEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DemoController {

    @Autowired
    private ProductoEntityRepository productoEntityRepo;


    // ------------------- INDEX -------------------
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("productos", productoEntityRepo.findAll());
        return "index";
    }


    @GetMapping("/buscar-producto")
    @ResponseBody
    public List<ProductoEntity> buscarProducto(
            @RequestParam("color") String color,
            @RequestParam("marca") String marca) {

        return productoEntityRepo.findByColorIgnoreCaseAndMarcaIgnoreCase(color, marca);
    }


    // ------------------- OTROS -------------------
    @GetMapping("/menu")
    public String menu() { return "menu"; }

    @GetMapping("/contacto")
    public String contacto() { return "contacto"; }

}

