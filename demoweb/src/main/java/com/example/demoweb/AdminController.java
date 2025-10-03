package com.example.demoweb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demoweb.Usuario;
import com.example.demoweb.Producto;

@Controller
public class AdminController {
    
    // Lista de usuarios 
    private List<Usuario> usuarios = null;
    
    // Lista de productos para el administrador
    private List<Producto> productos = null;
    
    // Usuario actualmente logueado
    private Usuario usuarioActual = null;
    
    // Constructor
    public AdminController() {
        // Inicializar usuarios
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario("admin", "admin123", "ADMIN", "Administrador Principal"));
        usuarios.add(new Usuario("admin2", "admin1234", "ADMIN", "Administrador David"));
        usuarios.add(new Usuario("cliente", "123", "CLIENTE", "Cliente Normal"));
        usuarios.add(new Usuario("usuario1", "pass123", "CLIENTE", "Roberto Gomez"));
        
        // Inicializar productos de ropa
        productos = new ArrayList<>();
        productos.add(new Producto(1, "Camisa Elegante", 89.90, 40, "Blanco"));
        productos.add(new Producto(2, "Jean Clásico", 129.90, 35, "Azul"));
        productos.add(new Producto(3, "Chaqueta Casual", 149.90, 20, "Negro"));
        productos.add(new Producto(4, "Gorra Deportiva", 59.90, 60, "Gris"));
        productos.add(new Producto(5, "Polo Básico", 49.90, 50, "Blanco"));
        productos.add(new Producto(6, "Casaca Deportiva", 159.90, 25, "Azul Marino"));
        productos.add(new Producto(7, "Chompa de Lana", 119.90, 15, "Gris"));
        productos.add(new Producto(8, "Blusa Formal", 89.90, 30, "Celeste"));
        productos.add(new Producto(9, "Falda plisada estampada", 99.90, 18, "Rosa"));
        productos.add(new Producto(10, "Short de Verano", 69.90, 45, "Beige"));
        productos.add(new Producto(11, "Parka Impermeable", 249.90, 10, "Negro"));
        productos.add(new Producto(12, "Campera de Cuero", 299.90, 12, "Marrón"));
        productos.add(new Producto(13, "Chaqueta Denim", 179.90, 20, "Azul"));
        productos.add(new Producto(14, "Sudadera Unisex", 109.90, 40, "Gris"));
    }
    
    // Mostrar página de login
    @GetMapping("/inicio-sesion")
    public String mostrarLogin() {
        return "inicio-sesion";
    }
    
    // Procesar el login
    @PostMapping("/login")
    public String procesarLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               RedirectAttributes redirectAttributes) {
        
        // Buscar usuario en la lista
        Usuario usuario = usuarios.stream()
            .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
            .findFirst()
            .orElse(null);
        
        if (usuario != null) {
            usuarioActual = usuario;
            System.out.println("Login exitoso: " + usuario);
            
            // Redirigir según el rol
            if ("ADMIN".equals(usuario.getRol())) {
                redirectAttributes.addFlashAttribute("nombreAdmin", usuario.getNombreCompleto());
                return "redirect:/admin/almacenamiento";
            } else {
                return "redirect:/";
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Usuario o contraseña incorrectos");
            return "redirect:/inicio-sesion";
        }
    }
    
    // Página de almacenamiento para administradores
    @GetMapping("/admin/almacenamiento")
    public String mostrarAlmacenamiento(Model model) {
        if (usuarioActual != null && "ADMIN".equals(usuarioActual.getRol())) {
            model.addAttribute("nombreAdmin", usuarioActual.getNombreCompleto());
            model.addAttribute("listaProductos", productos);
            return "admin-almacenamiento";
        } else {
            return "redirect:/inicio-sesion";
        }
    }

    // Ir a página de edición con PathVariable
    @GetMapping("/admin/editar/{id}")
    public String irEditarProducto(@PathVariable("id") int id, Model model) {
        System.out.println("Editando producto con id: " + id);
        
        Producto producto = productos.stream()
            .filter(p -> p.getId() == id)
            .findFirst()
            .orElse(null);
        
        if (producto != null && usuarioActual != null && "ADMIN".equals(usuarioActual.getRol())) {
            model.addAttribute("productoEdicion", producto);
            model.addAttribute("nombreAdmin", usuarioActual.getNombreCompleto());
            return "editar-almacenamiento";
        } else {
            return "redirect:/inicio-sesion";
        }
    }
    
    // Guardar cambios del producto editado
    @PostMapping("/admin/guardar-producto")
    public String guardarProducto(@ModelAttribute Producto productoActualizado) {
        System.out.println("Guardando producto: " + productoActualizado);
        
        for (Producto p : productos) {
            if (p.getId() == productoActualizado.getId()) {
                p.setNombre(productoActualizado.getNombre());
                p.setPrecio(productoActualizado.getPrecio());
                p.setStock(productoActualizado.getStock());
                p.setColor(productoActualizado.getColor());
                break;
            }
        }
        
        return "redirect:/admin/almacenamiento";
    }
    
    // Cerrar sesión
    @GetMapping("/logout")
    public String cerrarSesion() {
        usuarioActual = null;
        return "redirect:/";
    }
}