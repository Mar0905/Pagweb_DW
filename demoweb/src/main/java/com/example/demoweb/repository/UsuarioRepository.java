package com.example.demoweb.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoweb.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	// consulta para buscar el usario por gmail
 Optional<Usuario> findByEmail(String email);
 // comprobar el gmail
 boolean existsByEmail(String email);
}