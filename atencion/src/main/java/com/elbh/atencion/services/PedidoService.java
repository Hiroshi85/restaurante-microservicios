package com.elbh.atencion.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elbh.atencion.Dto.DetallePedido;
import com.elbh.atencion.Dto.PedidoConDetalle;
import com.elbh.atencion.entities.Mesa;
import com.elbh.atencion.entities.Pedido;
import com.elbh.atencion.repositories.MesaRepository;
import com.elbh.atencion.repositories.PedidoRepository;
import com.elbh.atencion.repositories.external.DetallePedidoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository repo;
    private final MesaRepository mesaRepo;
    private final DetallePedidoRepository detalleRepo;

    public PedidoConDetalle findById(Integer id){
        Pedido pedido = repo.findById(id).orElse(null);
        if(pedido == null){
            return null;
        }
        List<DetallePedido> detalles = detalleRepo.getByIdPedido(id);
        PedidoConDetalle pedidoConDetalle = new PedidoConDetalle(pedido.getCreatedAt());
        pedidoConDetalle.setId(pedido.getId());
        pedidoConDetalle.setIdMesa(pedido.getMesa().getId());
        pedidoConDetalle.setMesa(pedido.getMesa().getCodigo());
        pedidoConDetalle.setDetalles(detalles);
        return pedidoConDetalle;
    }

    public PedidoConDetalle savePedidoConDetalle(PedidoConDetalle pedidoConDetalle){
        Mesa mesa = mesaRepo.findById(pedidoConDetalle.getIdMesa()).orElse(null);
        Pedido pedido = new Pedido(null, mesa, null);
        pedido = repo.save(pedido);

        for(DetallePedido detalle : pedidoConDetalle.getDetalles()){
            detalle.setId(null);
            detalle.setIdPedido(pedido.getId());
        }

        //guardar detalles
        detalleRepo.saveAll(pedidoConDetalle.getDetalles());
        List<DetallePedido> detalles = detalleRepo.getByIdPedido(pedido.getId());
        PedidoConDetalle pcd = new PedidoConDetalle(
            pedido.getId(),
            pedido.getMesa().getId(),
            pedido.getMesa().getCodigo(),
            pedido.getCreatedAt(),
            detalles
        );
        return pcd;

    }

    public Pedido update(Integer id, Pedido pedido){
        pedido.setId(id);
        return repo.save(pedido);
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }
}
