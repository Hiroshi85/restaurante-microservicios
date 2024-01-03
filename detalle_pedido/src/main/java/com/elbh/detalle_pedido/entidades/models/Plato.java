package com.elbh.detalle_pedido.entidades.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plato {
    private int id;
    private String descripcion;
    private String categoria;
}
