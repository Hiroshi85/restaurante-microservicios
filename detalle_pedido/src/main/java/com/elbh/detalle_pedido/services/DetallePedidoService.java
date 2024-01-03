package com.elbh.detalle_pedido.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.elbh.detalle_pedido.entidades.DetallePedido;
import com.elbh.detalle_pedido.entidades.DetallePedidoPlato;
import com.elbh.detalle_pedido.entidades.models.PlatoResponse;
import com.elbh.detalle_pedido.repositories.DetallePedidoRepository;
import com.elbh.detalle_pedido.repositories.PlatoRespository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetallePedidoService {
    private final DetallePedidoRepository repo;
    private final PlatoRespository platoRepo;

    public ArrayList<DetallePedidoPlato> getAllByIdPedido(Integer id_pedido) {
        List<DetallePedido> lista = repo.findById_pedido(id_pedido);
        ArrayList<DetallePedidoPlato> newLista = new ArrayList<>();
        for (DetallePedido detallePedido : lista) {
            DetallePedidoPlato detalle = new DetallePedidoPlato(
                detallePedido.getId(),
                detallePedido.getId_pedido(),
                detallePedido.getId_plato(),
                detallePedido.getCantidad(),
                detallePedido.getPrecio()
            );
            PlatoResponse pr = platoRepo.findById(detallePedido.getId_plato());
            detalle.setDescripcion(pr.getDescripcion());
            newLista.add(detalle);
        }
        return newLista;
    }

    public DetallePedido save(DetallePedido detallePedido) {
        PlatoResponse pr = platoRepo.findById(detallePedido.getId_plato());
        detallePedido.setId(null);
        detallePedido.setPrecio(pr.getPrecio());
        return repo.save(detallePedido);
    }

    public DetallePedido findById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
