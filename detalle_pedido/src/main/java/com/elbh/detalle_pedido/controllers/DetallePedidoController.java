package com.elbh.detalle_pedido.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elbh.detalle_pedido.services.DetallePedidoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/detalle_pedido")
public class DetallePedidoController {
    private final DetallePedidoService service;
}
