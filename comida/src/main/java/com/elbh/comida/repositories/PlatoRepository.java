package com.elbh.comida.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elbh.comida.entities.Plato;

@Repository
public interface PlatoRepository extends JpaRepository<Plato, Integer> {
    
}
