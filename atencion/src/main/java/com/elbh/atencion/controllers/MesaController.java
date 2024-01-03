package com.elbh.atencion.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elbh.atencion.entities.Mesa;
import com.elbh.atencion.services.MesaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mesas")
public class MesaController {
    private final MesaService service;

    @GetMapping
    public List<Mesa> index(){
        return service.listarMesas();
    }

    @GetMapping("/{id}")
    public Mesa show(@PathVariable Integer id){
        return service.findById(id);
    }

    @PostMapping
    public Mesa store(@RequestBody Mesa mesa){
        return service.save(mesa);
    }

    @PutMapping("/{id}")
    public Mesa update(@PathVariable Integer id, @RequestBody Mesa mesa){
        return service.update(id, mesa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable Integer id){
        service.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
