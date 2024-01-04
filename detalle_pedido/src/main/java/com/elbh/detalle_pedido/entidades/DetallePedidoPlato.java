package com.elbh.detalle_pedido.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedidoPlato {
    private Integer id;
    private Integer idPedido;
    private Integer idPlato;
    private Integer cantidad;
    private String plato;
    private Double precio;

    public DetallePedidoPlato(Integer id, Integer id_pedido, Integer id_plato, Integer cantidad, Double precio){
        this.id = id;
        this.idPedido = id_pedido;
        this.idPlato = id_plato;
        this.cantidad = cantidad;
        this.precio = precio;
    }
}
