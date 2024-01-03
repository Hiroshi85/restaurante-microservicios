package com.elbh.atencion.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elbh.atencion.entities.Mesa;
import com.elbh.atencion.repositories.MesaRepository;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class MesaService {
    private final MesaRepository repo;

    public List<Mesa> listarMesas() {
        return repo.findAll();
    }

    public Mesa findByCodigo(Integer codigo) {
        val mesas = repo.findByCodigo(codigo);
        if(mesas.isEmpty()) {
            return null;
        }
        return mesas.get(0);
    }

    public Mesa save(Mesa mesa) {
        return repo.save(mesa);
    }

    public Mesa update(Integer id, Mesa mesa) {
        mesa.setId(id);
        return repo.save(mesa);
    }

    public Mesa findById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
