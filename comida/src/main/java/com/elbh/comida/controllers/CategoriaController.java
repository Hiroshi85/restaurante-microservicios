package com.elbh.comida.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elbh.comida.entities.Categoria;
import com.elbh.comida.services.CategoriaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;

    @GetMapping
    public ArrayList<Categoria> index(){
        return categoriaService.getCategorias();
    }

    @GetMapping("/{id}")
    public Categoria show(@PathVariable Integer id){
        return categoriaService.getCategoria(id);
    }

    @PostMapping
    public Categoria store(@RequestBody Categoria categoria){
        return categoriaService.saveCategoria(categoria);
    }

    @DeleteMapping("/{id}")
    public void destroy(@PathVariable Integer id){
        categoriaService.deleteCategoria(id);
    }

    @PutMapping("/{id}")
    public Categoria update(@PathVariable Integer id, @RequestBody Categoria categoria){
        return categoriaService.updateCategoria(id, categoria);
    }
}
