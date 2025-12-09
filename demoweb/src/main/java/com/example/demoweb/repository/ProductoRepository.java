package com.example.demoweb.repository;


import com.example.demoweb.model.Producto;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
@Primary
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByActivoTrue(); 
    List<Producto> findByActivoTrueOrderByIdProductoAsc();
    List<Producto> findByNombreContainingIgnoreCase(String nombre); 
}
