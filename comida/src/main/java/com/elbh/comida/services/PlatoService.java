package com.elbh.comida.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.elbh.comida.dto.PlatoRequestDTO;
import com.elbh.comida.entities.Categoria;
import com.elbh.comida.entities.Plato;
import com.elbh.comida.repositories.PlatoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PlatoService {
    private final PlatoRepository platoRepo;

    public Plato getPlato(Integer id){
        return platoRepo.findById(id).orElse(null);
    }

    public ArrayList<Plato> getPlatos(){
        return new ArrayList<Plato>(platoRepo.findAll());
    }

    public Plato savePlato(PlatoRequestDTO platoDto){
        Plato plato = new Plato(
            null,
            platoDto.getDescripcion(),
            platoDto.getPrecio(),
            null,
            new Categoria(platoDto.getIdCategoria(), null)
        );
        return platoRepo.saveAndFlush(plato);
    }

    public void deletePlato(Integer id){
        platoRepo.deleteById(id);
    }   

    public Plato updatePlato(Integer id, PlatoRequestDTO platoDto){
        Plato existingPlato = this.getPlato(id);
        existingPlato.setDescripcion(platoDto.getDescripcion());
        existingPlato.setPrecio(platoDto.getPrecio());
        Integer idCategoria = platoDto.getIdCategoria() != null ? platoDto.getIdCategoria() : existingPlato.getCategoria().getId();
        existingPlato.setCategoria(new Categoria(idCategoria, null));
        return platoRepo.save(existingPlato);
    }


}
