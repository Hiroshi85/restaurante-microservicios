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

import com.elbh.comida.dto.PlatoRequestDTO;
import com.elbh.comida.entities.Plato;
import com.elbh.comida.services.PlatoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/platos")
public class PlatoController {
    private final PlatoService platoService;

    @GetMapping
    public ArrayList<Plato> index(){
        return platoService.getPlatos();
    }

    @GetMapping("/{id}")
    public Plato show(@PathVariable Integer id){
        return platoService.getPlato(id);
    }

    @PostMapping
    public Plato store(@RequestBody PlatoRequestDTO plato){
        return platoService.savePlato(plato);
    }

    @DeleteMapping("/{id}")
    public void destroy(@PathVariable Integer id){
        platoService.deletePlato(id);
    }

    @PutMapping("/{id}")
    public Plato update(@PathVariable Integer id, @RequestBody PlatoRequestDTO plato){
        return platoService.updatePlato(id, plato);
    }
}
