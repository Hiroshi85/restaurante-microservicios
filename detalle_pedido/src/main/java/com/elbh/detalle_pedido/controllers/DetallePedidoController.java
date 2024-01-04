package com.elbh.detalle_pedido.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elbh.detalle_pedido.entidades.DetallePedido;
import com.elbh.detalle_pedido.entidades.DetallePedidoPlato;
import com.elbh.detalle_pedido.services.DetallePedidoService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/detalle_pedido")
public class DetallePedidoController {
    private final DetallePedidoService service;

    @GetMapping("/{id}")
    public List<DetallePedidoPlato> show(@PathVariable Integer id){
        val list = service.getAllByIdPedido(id);
        return list;
    }

    @PostMapping
    public DetallePedido save(@RequestBody DetallePedido detallePedido){
        return service.save(detallePedido);
    }

    @PostMapping("/all")
    public ResponseEntity<List<DetallePedido>> saveAll(@RequestBody List<DetallePedido> lista){
        val list = service.saveAll(lista);
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{id}")
    public void destroy(@PathVariable Integer id){
        service.deleteById(id);
    }
}
