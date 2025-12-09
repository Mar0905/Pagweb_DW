package com.example.demoweb.service;

import com.example.demoweb.model.Producto;
import com.example.demoweb.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repo;

    public List<Producto> listarTodos() {
        return repo.findAll();
    }

    public List<Producto> listarActivos() {
        return repo.findByActivoTrueOrderByIdProductoAsc();
    }

    public Optional<Producto> porId(Long id) {
        return repo.findById(id);
    }

    public Producto guardar(Producto p) {
        return repo.save(p);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}