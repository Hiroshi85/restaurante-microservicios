package com.elbh.atencion.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.elbh.atencion.Dto.PedidoConDetalle;
import com.elbh.atencion.services.PedidoService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequiredArgsConstructor
@RestController
@RequestMapping
public class PedidoController {
    private final PedidoService service;
    
    @GetMapping("/{id}")
    public ResponseEntity<PedidoConDetalle> getPedido(@PathVariable Integer id) {
        PedidoConDetalle pedido = service.findById(id);
        if(pedido == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    public PedidoConDetalle savePedidoConDetalle(@RequestBody PedidoConDetalle body){
        return service.savePedidoConDetalle(body);
    }
}
