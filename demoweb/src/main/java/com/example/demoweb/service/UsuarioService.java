package com.example.demoweb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoweb.model.Usuario;
import com.example.demoweb.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // LOGIN
    public Optional<Usuario> autenticar(String email, String password) {
        return usuarioRepository.findByEmail(email)
                .filter(u -> u.getPassword().equals(password));
    }

    // REGISTRO — NUEVO
    public Usuario registrar(Usuario usuario) {

        // Verificar si existe email duplicado
        Optional<Usuario> existe = usuarioRepository.findByEmail(usuario.getEmail());
        if (existe.isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado.");
        }

        // Rol por defecto 
        if (usuario.getRol() == null || usuario.getRol().isEmpty()) {
            usuario.setRol("cliente");
        }

        // Guardar en BD usando JPA
        return usuarioRepository.save(usuario);
    }

    // Buscar por email
    public Optional<Usuario> porEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}

