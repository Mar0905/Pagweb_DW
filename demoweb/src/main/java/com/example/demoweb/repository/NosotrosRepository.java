package com.example.demoweb.repository;

import com.example.demoweb.model.Nosotros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NosotrosRepository extends JpaRepository<Nosotros, Long> {
}
