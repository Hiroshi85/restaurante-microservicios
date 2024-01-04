package com.elbh.atencion.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.elbh.atencion.Dto.PedidoConDetalle;
import com.elbh.atencion.services.PedidoService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequiredArgsConstructor
@RestController
@RequestMapping
public class PedidoController {
    private final PedidoService service;
    
    @GetMapping
    public PedidoConDetalle getPedido(@RequestParam Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public PedidoConDetalle savePedidoConDetalle(@RequestBody PedidoConDetalle body){
        return service.savePedidoConDetalle(body);
    }
}
