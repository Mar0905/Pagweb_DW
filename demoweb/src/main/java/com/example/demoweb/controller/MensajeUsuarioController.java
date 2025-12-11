package com.example.demoweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demoweb.model.Contacto;
import com.example.demoweb.model.Usuario;
import com.example.demoweb.repository.ContactoRepository;

import jakarta.servlet.http.HttpSession;


@Controller
public class MensajeUsuarioController {

    @Autowired
    private ContactoRepository contactoRepository;

    @GetMapping("/mis-mensajes")
    public String verMisMensajes(Model model, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // PASO 1: Verificar si hay un usuario logueado
        if (usuario == null) {
            // Si no está logueado, lo redirigimos a la página de login
            return "redirect:/login"; 
        }

        // PASO 2: Buscar mensajes filtrando por su correo
        // Usamos el método findByCorreo que definimos en el Repository
        model.addAttribute("mensajes", 
                contactoRepository.findByCorreo(usuario.getEmail()));

        // PASO 3: Devolver la vista Thymeleaf
        return "mis-mensajes";
    }
    

 @GetMapping("/mensaje/eliminar/{id}")
 public String eliminarMensaje(@PathVariable("id") Long id, HttpSession session) {
     Usuario usuario = (Usuario) session.getAttribute("usuario");

     // 1. Verificar sesión
     if (usuario == null) return "redirect:/login";
     
     // 2. Intentar encontrar y borrar el mensaje
     try {
         var c = contactoRepository.findById(id).orElse(null);

         // 3. Verificar que el mensaje exista y que pertenezca al usuario logueado
         if (c != null && c.getCorreo().equals(usuario.getEmail())) {
             contactoRepository.delete(c);
         }
     } catch (Exception e) {
         // En caso de error de la base de datos, imprime el error para depurar
         System.err.println("Error al eliminar mensaje con ID: " + id);
         e.printStackTrace(); 
     }

     // 4. Redirigir de vuelta a la lista de mensajes
     return "redirect:/mis-mensajes";
 }
    

//------------------- EDICIÓN (GET) -------------------
@GetMapping("/mensaje/editar/{id}")
public String editarMensaje(@PathVariable("id") Long id, Model model, HttpSession session) {
  Usuario usuario = (Usuario) session.getAttribute("usuario");

  if (usuario == null) return "redirect:/login";

  var c = contactoRepository.findById(id).orElse(null);

  // Si el mensaje no existe o no pertenece al usuario, redirigir
  if (c == null || !c.getCorreo().equals(usuario.getEmail())) {
      return "redirect:/mis-mensajes";
  }

  // Pasar el objeto Contacto encontrado al modelo para llenar el formulario
  model.addAttribute("mensaje", c);
  return "editar-mensaje";  
}

//------------------- EDICIÓN (POST) -------------------
@PostMapping("/mensaje/editar")
public String guardarEdicion(@ModelAttribute Contacto contacto, HttpSession session) {

  Usuario usuario = (Usuario) session.getAttribute("usuario");

  if (usuario == null) return "redirect:/login";
  
  // Intentamos guardar los cambios
  try {
      // 1. Obtener el mensaje original de la BD usando el ID del formulario
      var original = contactoRepository.findById(contacto.getId()).orElse(null);

      // 2. Verificar que el mensaje sea válido y que pertenezca al usuario
      if (original == null || !original.getCorreo().equals(usuario.getEmail())) {
          // Si hay un problema, no hacer nada y redirigir
          return "redirect:/mis-mensajes";
      }

      // 3. Actualizar solo el campo editable (mensaje)
      original.setMensaje(contacto.getMensaje());
      
      // 4. Guardar la entidad actualizada
      contactoRepository.save(original);
      
  } catch (Exception e) {
      System.err.println("Error al guardar la edición del mensaje con ID: " + contacto.getId());
      e.printStackTrace(); 
  }

  return "redirect:/mis-mensajes";
}
 
}
