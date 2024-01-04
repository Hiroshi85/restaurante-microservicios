package com.elbh.detalle_pedido.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elbh.detalle_pedido.entidades.DetallePedido;
import java.util.List;


@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer>{
    List<DetallePedido> findByIdPedido(Integer id_pedido);
}
