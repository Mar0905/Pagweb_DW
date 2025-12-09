package com.example.demoweb.repository;

import com.example.demoweb.model.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoEntityRepository extends JpaRepository<ProductoEntity, Long> {

    List<ProductoEntity> findByColorIgnoreCaseAndMarcaIgnoreCase(String color, String marca);
}