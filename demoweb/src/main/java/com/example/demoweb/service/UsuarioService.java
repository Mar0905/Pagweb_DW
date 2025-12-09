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

    public Optional<Usuario> autenticar(String email, String password) {
        // buscar el usario por el eamil y validar la contraseña
        return usuarioRepository.findByEmail(email)
                .filter(u -> u.getPassword().equals(password));
    }
}