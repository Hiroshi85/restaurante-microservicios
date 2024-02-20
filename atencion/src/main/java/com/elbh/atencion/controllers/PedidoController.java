package com.elbh.atencion.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.elbh.atencion.Dto.PedidoConDetalle;
import com.elbh.atencion.entities.Pedido;
import com.elbh.atencion.services.PedidoService;

import lombok.RequiredArgsConstructor;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedido() {
        List<Pedido> pedidos = service.listarPedidos();
        if(pedidos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidos);
    }

    @PostMapping
    public PedidoConDetalle savePedidoConDetalle(@RequestBody PedidoConDetalle body){
        return service.savePedidoConDetalle(body);
    }

    @GetMapping("/pdf/{id}")
    public ResponseEntity<byte[]> getPedidoPdf(@PathVariable Integer id) {
        byte[] pdf = service.getPedidoPDF(id);
        return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=boleta-"+id+".pdf")
            .body(pdf);
    }
}
