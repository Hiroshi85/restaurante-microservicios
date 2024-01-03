package com.elbh.detalle_pedido.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedidoPlato {
    private Integer id;
    private Integer id_pedido;
    private Integer id_plato;
    private Integer cantidad;
    private String descripcion;
    private Double precio;

    public DetallePedidoPlato(Integer id, Integer id_pedido, Integer id_plato, Integer cantidad, Double precio){
        this.id = id;
        this.id_pedido = id_pedido;
        this.id_plato = id_plato;
        this.cantidad = cantidad;
        this.precio = precio;
    }
}
