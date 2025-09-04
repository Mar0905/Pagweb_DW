package com.example.demoweb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

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
}
