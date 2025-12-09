package com.example.demoweb.repository;

import com.example.demoweb.model.Producto;


import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoEntityRepository extends ProductoRepository {

    List<Producto> findByColorIgnoreCaseAndMarcaIgnoreCase(String color, String marca);

}