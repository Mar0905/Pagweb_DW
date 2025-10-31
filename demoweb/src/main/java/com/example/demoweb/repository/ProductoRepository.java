package com.example.demoweb.repository;

import com.example.demoweb.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByActivoTrue(); 
    List<Producto> findByActivoTrueOrderByIdProductoAsc();// para página pública
    List<Producto> findByNombreContainingIgnoreCase(String nombre); // búsqueda
}