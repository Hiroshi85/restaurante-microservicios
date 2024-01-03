package com.elbh.detalle_pedido.entidades;

import com.elbh.detalle_pedido.entidades.models.Plato;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedidoPlato {
    private int id;
    private int id_pedido;
    private int cantidad;
    private double precio;
    private Plato plato;
}
