package com.elbh.comida.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.elbh.comida.entities.Categoria;
import com.elbh.comida.repositories.CategoriaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepo;

    public Categoria getCategoria(Integer id){
        return categoriaRepo.findById(id).orElse(null);
    }

    public ArrayList<Categoria> getCategorias(){
        return new ArrayList<Categoria>(categoriaRepo.findAll());
    }

    public Categoria saveCategoria(Categoria categoria){
        return categoriaRepo.save(categoria);
    }

    public void deleteCategoria(Integer id){
        categoriaRepo.deleteById(id);
    }

    public Categoria updateCategoria(Integer id, Categoria categoria){
        Categoria existingCategoria = this.getCategoria(id);
        existingCategoria.setDescripcion(categoria.getDescripcion());
        return categoriaRepo.save(existingCategoria);
    }
}
