package com.elbh.atencion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elbh.atencion.entities.Mesa;
import java.util.List;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Integer> {
    public List<Mesa> findByCodigo(Integer codigo);
}
