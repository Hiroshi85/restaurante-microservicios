package com.elbh.detalle_pedido.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elbh.detalle_pedido.entidades.DetallePedido;
import com.elbh.detalle_pedido.repositories.DetallePedidoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetallePedidoService {
    private final DetallePedidoRepository repo;

    public List<DetallePedido> getByIdPedido(Integer id_pedido) {
        return repo.findById_pedido(id_pedido);
    }

    public DetallePedido save(DetallePedido detallePedido) {
        return repo.save(detallePedido);
    }

    public DetallePedido findById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
