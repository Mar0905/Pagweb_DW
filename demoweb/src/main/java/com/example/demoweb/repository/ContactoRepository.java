package com.example.demoweb.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demoweb.model.Contacto;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long> {

    // Buscar mensajes por correo del usuario
    List<Contacto> findByCorreo(String correo);
}